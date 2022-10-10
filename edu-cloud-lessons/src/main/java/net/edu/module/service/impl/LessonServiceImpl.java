package net.edu.module.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.framework.security.user.SecurityUser;
import net.edu.framework.security.user.UserDetail;
import net.edu.module.api.EduJudgeApi;
import net.edu.module.api.EduTeachApi;
import net.edu.module.convert.LessonConvert;
import net.edu.module.dao.LessonAttendLogDao;
import net.edu.module.entity.LessonEntity;
import net.edu.module.query.LessonQuery;
import net.edu.module.service.LessonAttendLogService;
import net.edu.module.service.LessonProblemService;
import net.edu.module.service.LessonResourceService;
import net.edu.module.vo.*;
import net.edu.module.dao.LessonDao;
import net.edu.module.service.LessonService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
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
public class LessonServiceImpl extends BaseServiceImpl<LessonDao, LessonEntity> implements LessonService {

    private final LessonProblemService lessonProblemService;
    private final LessonResourceService lessonResourceService;
    private final LessonAttendLogService lessonAttendLogService;
    private final EduTeachApi eduTeachApi;
    private final RedisUtils redisUtils;
    private final LessonDao lessonDao;
    private final EduJudgeApi eduJudgeApi;

    /**
     * 获取学生/老师的课堂记录
     * @param query
     * @return
     */
    @Override
    public PageResult<LessonVO> page(LessonQuery query) {
        UserDetail user=SecurityUser.getUser();
        query.setUserId(user.getId());
        if(!CollUtil.isEmpty(user.getRoleIdList())){
            query.setRole(user.getRoleIdList().get(0));
        }else {
            query.setRole(0L);//管理员
        }
        PageResult<LessonVO> pageResult=null;
            Page<LessonVO> page = new Page<>(query.getPage(),query.getLimit());
            IPage<LessonVO> list;
            //判断是否为学生
            if(query.getRole()==2L){
                list = baseMapper.selectStudentPage(page, query);
            }else {
                list = baseMapper.selectTeacherPage(page, query);
            }
            pageResult=  new PageResult<>(list.getRecords(), list.getTotal());
        return pageResult;
    }

    @Override
    public List<LessonVO> list(LessonQuery query) {
        List<LessonEntity> list = null;
        LambdaQueryWrapper<LessonEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(true, LessonEntity::getClassId, query.getClassId());
        wrapper.orderByAsc(LessonEntity::getSort);
        list = baseMapper.selectList(wrapper);
        return LessonConvert.INSTANCE.convertList(list);
    }

    @Override
    public void update(LessonVO vo) {
        LessonEntity entity = LessonConvert.INSTANCE.convert(vo);
        updateById(entity);
    //    redisUtils.del(RedisKeys.getClassLesson(vo.getClassId()));
    }

    @Override
    public void updateHomework(LessonVO vo) {
        baseMapper.updateLesson(vo);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createLessons(List<LessonVO> voList) {

        if (!CollectionUtil.isEmpty(voList)) {
            //班级学生列表
            List<Long> userList=eduTeachApi.list(voList.get(0).getClassId()).getData();
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
    public PageResult<LessonVO> homeworkPage(LessonQuery query) {
        Page<LessonVO> page = new Page<>(query.getPage(),query.getLimit());
        query.setUserId(SecurityUser.getUserId());
        IPage<LessonVO> list;
        list = baseMapper.selectHomeworkPage(page, query);
        return new PageResult<>(list.getRecords(), list.getTotal());
    }

    @Override
    public void handlerStatisticsMonthlyScheduled() {
//        获取表中所有作业状态为1的课程信息
        List<LessonVO> list = baseMapper.selectLessonIdList();
        for (LessonVO lessonVO : list) {
            //判断当前时间是否大于结束时间
            if (lessonVO.getHomeworkEndTime().compareTo(new Date()) < 0) {
//                修改回家作业状态
                lessonVO.setHomeworkStatus(2);
//                更新到数据库
                update(lessonVO);
            }
        }

    }
    @Override
    public List<LessonVO> getListById(Long classId) {
        return lessonDao.getListById(classId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void homeWorkDeadline(){
        //获取所有留有回家作业并且作业结束时间已经截止,作业状态为1的课堂信息
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        LambdaQueryWrapper<LessonEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(LessonEntity::getHomeworkStatus,1);
        wrapper.le(LessonEntity::getHomeworkEndTime,format);
        List<LessonEntity> list = baseMapper.selectList(wrapper);
        //判断 list是否为空
        if(!CollUtil.isEmpty(list)){
            for(LessonEntity item:list){
                //找出学生及其完成情况
                List<LessonJudgeRecordVo> users= eduJudgeApi.getLessonProblemRecord(item.getId(),2).getData();
                //遍历学生
                //for
                if(!CollUtil.isEmpty(users)){
                    for (LessonJudgeRecordVo user:users){
                        //记录未完成的答题数
                        if(!CollUtil.isEmpty(user.getProblemRecords())){
                            int num= 0;
                            for (LessonProblemRecord record:user.getProblemRecords()){
                                if(record.getSubmitStatus() == null){
                                    num++;
                                }
                            }
                            //更新class_user
                            eduTeachApi.updateHomeworkTimes(user.getUserId(),item.getClassId(),num);
                        }
                    }
                }
                //循环结束更新作业状态
                item.setHomeworkStatus(2);
                LessonVO vo = LessonConvert.INSTANCE.convert(item);
                updateHomework(vo);
            }
        }

    }

}
