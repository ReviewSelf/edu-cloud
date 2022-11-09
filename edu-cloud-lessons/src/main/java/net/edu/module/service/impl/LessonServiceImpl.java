package net.edu.module.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.framework.security.user.SecurityUser;
import net.edu.framework.security.user.UserDetail;
import net.edu.module.api.EduJudgeApi;
import net.edu.module.api.EduTeachApi;
import net.edu.module.api.EduWxApi;
import net.edu.module.convert.LessonConvert;
import net.edu.module.entity.LessonEntity;
import net.edu.module.query.LessonQuery;
import net.edu.module.service.LessonAttendLogService;
import net.edu.module.service.LessonProblemService;
import net.edu.module.service.LessonResourceService;
import net.edu.module.utils.LessonExcelUtil;
import net.edu.module.vo.*;
import net.edu.module.dao.LessonDao;
import net.edu.module.service.LessonService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 课程表
 *
 * @author 马佳浩
 * @since 1.0.0 2022-09-15
 */
@Service
@AllArgsConstructor
@Slf4j
public class LessonServiceImpl extends BaseServiceImpl<LessonDao, LessonEntity> implements LessonService {

    private final LessonProblemService lessonProblemService;
    private final LessonResourceService lessonResourceService;
    private final LessonAttendLogService lessonAttendLogService;
    private final EduTeachApi eduTeachApi;
    private final LessonDao lessonDao;
    private final EduJudgeApi eduJudgeApi;

    private final RedisUtils redisUtils;

    private final LessonExcelUtil lessonExcelUtil;

    EduWxApi eduWxApi;


    /**
     * 获取学生/老师的课堂记录
     *
     * @param query
     * @return
     */
    @Override
    public PageResult<LessonVO> page(LessonQuery query) {
        UserDetail user = SecurityUser.getUser();
        query.setUserId(user.getId());
        if (!CollUtil.isEmpty(user.getRoleIdList())) {
            query.setRole(user.getRoleIdList().get(0));
        } else {
            //管理员
            query.setRole(0L);
        }
        Page<LessonVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<LessonVO> list;
        //判断是否为学生
        if (query.getRole() == 2L) {
            list = baseMapper.selectStudentPage(page, query);
        } else {
            list = baseMapper.selectTeacherPage(page, query);
        }
        return new PageResult<>(list.getRecords(), list.getTotal());
    }

    @Override
    public List<LessonVO> list(LessonQuery query) {
        LambdaQueryWrapper<LessonEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(true, LessonEntity::getClassId, query.getClassId());
        wrapper.orderByAsc(LessonEntity::getSort);
        List<LessonEntity> list = baseMapper.selectList(wrapper);
        return LessonConvert.INSTANCE.convertList(list);
    }

    @Override
    public void update(LessonVO vo) {

        LessonEntity entity = LessonConvert.INSTANCE.convert(vo);
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createLessons(List<LessonVO> voList) {

        if (!CollectionUtil.isEmpty(voList)) {
            //班级学生列表
            List<Long> userList = eduTeachApi.list(voList.get(0).getClassId()).getData();
            //第一堂课状态设置进行中
            voList.get(0).setStatus(0);

            for (int i = 0; i < voList.size(); i++) {
                LessonVO item = voList.get(i);
                //插入课程
                LessonEntity entity = LessonConvert.INSTANCE.convert(item);
                baseMapper.insert(entity);
                //生成第一堂课的学生签到表//插入签到表
                lessonAttendLogService.copyUserFromClassUser(userList, entity.getId());
                if (i == 0) {
                    //更新课堂下一堂课指向
                    eduTeachApi.updateNextLesson(entity.getId(), item.getClassId());
                }
                //拷贝教学题目，生成课堂题目
                lessonProblemService.copyFromPlanItem(item.getPlanItemId(), entity.getId());
                //拷贝教学资源，生成课堂资源
                lessonResourceService.copyFromPlanItem(item.getPlanItemId(), entity.getId());
            }


        }
    }


    @Override
    public List<LessonVO> getClassNotStartLesson(Long classId) {
        return lessonDao.getListById(classId);
    }

    @Override
    public List<LessonVO> getClassAllLesson(Long classId) {
        return lessonDao.getClassAllLesson(classId);
    }

    @Override
    public void updateHomework(LessonVO vo) {
        if (vo.getHomeworkStatus() == 2) {
            closeLessonHomeWork(vo.getId());
            redisUtils.del(RedisKeys.getHomeWorkKey(vo.getId()));
        } else {
            baseMapper.updateHomework(vo);

            //作业发布微信推送
            LessonService lessonService= SpringUtil.getBean(LessonService.class);
            lessonService.sendHomeworkBegin(vo.getId());

            //作业截止微信推送
            long deadLineTime = vo.getHomeworkEndTime().getTime() - System.currentTimeMillis() - 1000*60*60*24L;
            if(deadLineTime > 0) {
                redisUtils.set(RedisKeys.getHomeworkEndKey(vo.getId() , vo.getHomeworkEndTime()) , deadLineTime , deadLineTime / 1000);
            }

            long time = vo.getHomeworkEndTime().getTime() - System.currentTimeMillis();
            if (time > 0) {
                redisUtils.set(RedisKeys.getHomeWorkKey(vo.getId()), time, time / 1000);
            }
        }
    }

    @Async
    public void sendHomeworkBegin(Long lessonId){
        List<WxWorkPublishVO> list1 = lessonDao.selectHomeworkBegin(lessonId);
        eduWxApi.insertWorkPublishTemplate(list1);
    }

    public void sendHomeworkEnd(Long lessonId , String endTime) {
        List<LessonProblemRankVO> list1 = eduJudgeApi.getLessonProblemRank(lessonId, 2).getData();
        List<WxWorkDeadlineVO> list2 = new ArrayList<>();
        Date date = new Date();
        for(int i = 0; i < list1.size(); i++) {
            LessonProblemRankVO lessonProblemRankVO = list1.get(i);
            WxWorkDeadlineVO wxWorkDeadlineVO = new WxWorkDeadlineVO();
            wxWorkDeadlineVO.setSubmitMethod("手机端或电脑端");
            wxWorkDeadlineVO.setUserId(lessonProblemRankVO.getUserId());
            wxWorkDeadlineVO.setRemark("还有" + lessonProblemRankVO.getUnansweredNum() + "道题未完成，请及时完成作业！");
            wxWorkDeadlineVO.setDeadline(endTime);
            wxWorkDeadlineVO.setSendTime(String.valueOf(date));
            list2.add(wxWorkDeadlineVO);
        }
        eduWxApi.insertWorkDeadlineTemplate(list2);
    }


    @Override
    public PageResult<LessonVO> homeworkPage(LessonQuery query) {
        Page<LessonVO> page = new Page<>(query.getPage(), query.getLimit());
        query.setUserId(SecurityUser.getUserId());
        IPage<LessonVO> list;
        list = baseMapper.selectHomeworkPage(page, query);
        return new PageResult<>(list.getRecords(), list.getTotal());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void closeLessonHomeWork(Long lessonId) {
        LessonEntity entity = baseMapper.selectById(lessonId);
        List<LessonProblemRankVO> users = eduJudgeApi.getLessonProblemRank(entity.getId(), 2).getData();
        //遍历学生
        if (!CollUtil.isEmpty(users)) {
            for (LessonProblemRankVO user : users) {
                //记录未完成的答题数
                int num = user.getUnansweredNum();
                //更新class_user
                eduTeachApi.updateHomeworkTimes(user.getUserId(), entity.getClassId(), num);
            }
        }
        //循环结束更新作业状态
        entity.setHomeworkStatus(2);
        entity.setHomeworkEndTime(new Date());
        updateById(entity);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void homeWorkDeadline() {
        //获取所有留有回家作业并且作业结束时间已经截止,作业状态为1的课堂信息
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        LambdaQueryWrapper<LessonEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(LessonEntity::getHomeworkStatus, 1);
        wrapper.le(LessonEntity::getHomeworkEndTime, format);
        List<LessonEntity> list = baseMapper.selectList(wrapper);
        //判断 list是否为空
        if (!CollUtil.isEmpty(list)) {
            for (LessonEntity item : list) {
                //找出学生及其完成情况
                List<LessonProblemRankVO> users = eduJudgeApi.getLessonProblemRank(item.getId(), 2).getData();

                //遍历学生
                //for
                if (!CollUtil.isEmpty(users)) {
                    for (LessonProblemRankVO user : users) {
                        //记录未完成的答题数
                        int num = user.getUnansweredNum();
                        //更新class_user
                        eduTeachApi.updateHomeworkTimes(user.getUserId(), item.getClassId(), num);
                    }
                }
                //循环结束更新作业状态
                item.setHomeworkStatus(2);
                updateById(item);
            }

        }

    }

    @Override
    public void delete(Long classId) {
        baseMapper.deleteByClassId(classId);
    }

    @Override
    public void updateList(List<LessonVO> list) {
        for(int i=0;i<list.size();i++){
            baseMapper.updateList(list.get(i));
        }
    }

    @Override
    public void exportLesson(Long lessonId, HttpServletResponse response) throws IOException {
        System.out.println(eduJudgeApi.getLessonProblemRecord(lessonId));
        System.out.println(111111);
        List<LessonJudgeRecordVo> data =  eduJudgeApi.getLessonProblemRecord(lessonId).getData();

        List<String> header = new ArrayList<>();
        for (int j = 0;j<data.get(0).getProblemRecords().size();j++){
            header.add(data.get(0).getProblemRecords().get(j).getProblemName());
        }

        LessonEntity entity = baseMapper.selectById(lessonId);
        String bigTitle = "《"+entity.getName()+"》"+"\r\n"+"("+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(entity.getBeginTime()) +"-"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(entity.getEndTime())+")";

        lessonExcelUtil.examExportExcel(header,data,bigTitle,response);
    }

}
