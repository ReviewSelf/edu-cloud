package net.edu.module.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.exception.ServerException;
import net.edu.framework.common.page.PageResult;
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
import net.edu.module.vo.ExamVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
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

    private final ExamAttendLogDao examAttendLogDao;

    @Override
    public PageResult<ExamAttendLogVO> page(ExamAttendLogQuery query) {
        Page<ExamAttendLogVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<ExamAttendLogVO> list = baseMapper.page(page, query);
        return new PageResult<>(list.getRecords(), list.getTotal());
    }

    @Override
    public void save(ExamAttendLogVO vo) {
        ExamAttendLogEntity entity = ExamAttendLogConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public ExamAttendLogVO getUserExamAttend(Long examId) {
        Long userId = SecurityUser.getUserId();
        ExamAttendLogVO vo = baseMapper.selectUserAttendById(userId, examId);
        System.out.println(vo);
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
    public Boolean attendance(Long examId, Long userId) {
        ExamAttendLogVO vo = baseMapper.selectUserAttendById(userId, examId);
        System.out.println(vo);
        if (vo != null) {
            //存在考试
            Date date = new Date();
            if (vo.getBeginTime().getTime() <= date.getTime() && vo.getEndTime().getTime() >= date.getTime()) {
                //考试期间
                if (vo.getStatus() == 0) {
                    //未参与则签到

                    //加入考试
                    vo.setStatus(1);
                    vo.setJoinTime(date);
                    update(vo);
                    return true;
                } else if (vo.getStatus() == 1) {
                    if (vo.getFinishExamTime().getTime() < System.currentTimeMillis()) {
                        //考试截至，结束考试
                        vo.setStatus(2);
                        update(vo);
                        throw new ServerException("已交卷");
                    }
                    return true;
                } else if (vo.getStatus() == 2) {
                    //已交卷
                    throw new ServerException("已交卷");
                }
            } else {
                //非考试期间
                throw new ServerException("不在考试期间，不可参加考试");
            }

        }
        return false;
    }

    @Override
    public void update(ExamAttendLogVO vo) {
        ExamAttendLogEntity entity = ExamAttendLogConvert.INSTANCE.convert(vo);
        updateById(entity);
        redisUtils.del(RedisKeys.getExamAttendLog(vo.getExamId()));
    }

    @Override
    public void copyFromClass(List<Long> classIdList, Long examId) {
        System.out.println(classIdList);
        //查找班级对应的学生
        List<Long> userList = null;
        for (int i = 0; i < classIdList.size(); i++) {
            System.out.println(eduTeachApi.list(classIdList.get(i)).getData());
            Long classId = classIdList.get(i);
            userList.addAll(eduTeachApi.list(classId).getData());
        }
        //去重
        for (int i=0;i<classIdList.size();i++)
        {
            for (int j=i+1;j<classIdList.size();j++)
            {
                if(classIdList.get(i).equals(classIdList.get(j))){
                    classIdList.remove(j);
                }
            }
        }

        if (!CollUtil.isEmpty(userList)) {

            //insert
            examAttendLogDao.insertAttendLogFromClass(userList, examId);
        }


    }

    @Override
    public void updateExamStatus(Integer status, Long examId, Long userId, Date quitTime) {
        baseMapper.updateExamStatus(status, examId, userId, quitTime);
    }

    @Override
    public void updateAttendLogScore(ExamAttendLogVO vo) {
        examAttendLogDao.updateAttendLogScore(vo);
    }

    @Override
    public List<ExamAttendLogVO> getList(Long examId, Integer status, Integer isCorrecting) {
        List<ExamAttendLogVO> list = examAttendLogDao.selectList(examId, status, isCorrecting);
        return list;
    }

    @Override
    public void genExamInvitationCode(Long examId, String code, Long time) {
        redisUtils.set(RedisKeys.getExamInvitation(code), examId, time * 60L);
    }

    @Override
    public void receiveExamInvitation(String code) {
        Integer examId = (Integer) redisUtils.get(RedisKeys.getExamInvitation(code));
        if (examId == null) {
            throw new ServerException("邀请码错误");
        } else {
            Long userId = SecurityUser.getUserId();
            ExamAttendLogEntity entity = new ExamAttendLogEntity();
            entity.setExamId(Long.valueOf(examId));
            entity.setUserId(userId);
            try {
                baseMapper.insert(entity);
            } catch (Exception e) {
                if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                    throw new ServerException("已加入此考试，请勿重复参加");
                }
            }


        }
    }

    @Override
    public ExamAttendLogVO getUserExamInfo(Long userId, Long examId) {
        return baseMapper.selectUserExamInfo(userId, examId);
    }
}