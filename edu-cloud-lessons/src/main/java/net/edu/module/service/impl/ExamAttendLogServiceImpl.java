package net.edu.module.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import lombok.AllArgsConstructor;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.utils.DateUtils;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.api.EduTeachApi;
import net.edu.module.convert.ExamAttendLogConvert;
import net.edu.module.dao.ExamAttendLogDao;
import net.edu.module.entity.ExamAttendLogEntity;
import net.edu.module.entity.ExamEntity;
import net.edu.module.query.ExamAttendLogQuery;
import net.edu.module.service.ExamAttendLogService;
import net.edu.module.vo.ExamAttendLogVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 课堂签到表
 *
 * @author 马佳浩
 * @since 1.0.0 2022-09-15
 */
@Service
@AllArgsConstructor
public class ExamAttendLogServiceImpl extends BaseServiceImpl<ExamAttendLogDao, ExamAttendLogEntity> implements ExamAttendLogService {

    private final RedisUtils redisUtils;

    private final EduTeachApi eduTeachApi;

    @Override
    public ExamAttendLogVO getUserExamAttend(Long examId) {
        Long userId = SecurityUser.getUserId();
        ExamAttendLogVO vo = baseMapper.selectUserAttendById(userId, examId);

        Long finishDate = vo.getJoinTime().getTime() + vo.getTimeLimit() * 60 * 1000L;
        if (finishDate > vo.getEndTime().getTime()) {
            vo.setFinishExamTime(vo.getEndTime());
        } else {
            vo.setFinishExamTime(new Date(finishDate));
        }
        return vo;
    }


    @Override
    public List<ExamAttendLogVO> list(Long examId) {
        List<ExamAttendLogVO> list = null;
        list = (List<ExamAttendLogVO>) redisUtils.get(RedisKeys.getExamAttendLog(examId), RedisUtils.MIN_TEN_EXPIRE);
        if (CollectionUtil.isEmpty(list)) {
            list = baseMapper.selectUserAttend(examId);
            redisUtils.set(RedisKeys.getExamAttendLog(examId), list, RedisUtils.MIN_TEN_EXPIRE);
        }
        return list;
    }


    //名单校验加签到
    @Override
    public Boolean attendance(Long examId) {
        Long userId = SecurityUser.getUserId();
        ExamAttendLogVO vo = baseMapper.selectUserAttendById(userId, examId);
        if (vo != null) {
            //存在考试
            Date date = new Date();
            if (vo.getBeginTime().getTime() <= date.getTime() && vo.getEndTime().getTime() >= date.getTime()) {
                //考试期间
                if (vo.getStatus() == 0) {
                    //未参与则签到

                    //加入考试
                    vo.setStatus(1);
                    vo.setJoinTime(new Date());
                    update(vo);
                    return true;
                }
                else if(vo.getStatus()==1){
                    //进行中
                    return true;
                }
                else if(vo.getStatus()==2){
                    //已交卷
                    return false;
                }

            }else {
                //非考试期间
                return false;
            }


        }
//        Date date=new Date();
//        List<ExamAttendLogVO> userList=list(new ExamAttendLogQuery(lessonEntity.getId()));
//        if(!CollectionUtil.isEmpty(userList)){
//            for (ExamAttendLogVO vo:userList){
//                if(vo.getStuId().equals(userId)){
//                    //在上课范围，签到判断
//                    if(lessonEntity.getBeginTime().getTime()<=date.getTime() && lessonEntity.getEndTime().getTime()>=date.getTime()){
//                        if(vo.getStatus()!=1){
//                            vo.setStatus(1);
//                            vo.setCheckinTime(new Date());
//                            ExamAttendLogEntity entity = ExamAttendLogConvert.INSTANCE.convert(vo);
//                            updateById(entity);
//                            redisUtils.set(RedisKeys.getExamAttendLog(lessonEntity.getId()),userList,RedisUtils.MIN_TEN_EXPIRE);
//                        }
//                        //已签到返回
//                        return true;
//                    }
//                    //不在上课时间内，直接返回
//                    return true;
//                }
//            }
//        }
        return false;
    }


//    @Override
//    public void save(ExamAttendLogVO vo) {
//        ExamAttendLogEntity entity = ExamAttendLogConvert.INSTANCE.convert(vo);
//        baseMapper.insert(entity);
//    }

//
    @Override
    public void update(ExamAttendLogVO vo) {
        ExamAttendLogEntity entity = ExamAttendLogConvert.INSTANCE.convert(vo);
        updateById(entity);
        redisUtils.del(RedisKeys.getExamAttendLog(vo.getExamId()));
    }

    @Override
    public void copyFromClass(Long classId) {
        List<Long> userList=eduTeachApi.list(classId).getData();
        if(!CollUtil.isEmpty(userList)){
            //insert
        }
    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void delete(List<Long> idList) {
//        removeByIds(idList);
//    }
//
//    @Override
//    public void copyUserFromClassUser(List<Long> userList, Long lessonId) {
//        if (!CollectionUtil.isEmpty(userList)) {
//            baseMapper.insertUserList(userList, lessonId);
//        }
//        redisUtils.del(RedisKeys.getExamAttendLog(lessonId));
//    }
//
//    @Override
//    public void updateStudents(ExamAttendLogVO vo) {
//        // vo.setUpdateTime(new Date());
//        if (vo.getUserId() == null) {
//            vo.setUserId(SecurityUser.getUserId());
//        }
//        //根据学生id和课堂id找到唯一的记录进行修改
//        lessonAttendLogDao.updateStudents(vo);
//        redisUtils.del(RedisKeys.getExamAttendLog(vo.getExamId()));
//    }

}