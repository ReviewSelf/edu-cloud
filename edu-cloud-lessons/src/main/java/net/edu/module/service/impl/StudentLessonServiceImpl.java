package net.edu.module.service.impl;


import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.exception.ServerException;
import net.edu.framework.common.utils.HttpContextUtils;
import net.edu.framework.common.utils.IpUtils;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.entity.ExamProblemEntity;
import net.edu.module.entity.LessonEntity;
import net.edu.module.service.*;
import net.edu.module.vo.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/15 9:36
 * @Version: 1.0
 * @Description:
 */
@Service
@AllArgsConstructor
public class StudentLessonServiceImpl implements StudentLessonService {

    private final LessonIPService lessonIPService;
    private final LessonAttendLogService lessonAttendLogService;

    private final LessonService lessonService;

    private final ExamIPService examIPService;

    private final ExamAttendLogService examAttendLogService;
    private final ExamProblemService examProblemService;

    private final RedisUtils redisUtils;


    @Override
    public Result<String> attendLesson(Long lessonId) {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        assert request != null;
        String ip = IpUtils.getIpAddr(request);
        //ip校验
        if (!lessonIPService.ipJudge(lessonId, ip)) {
            return Result.error("不在ip白名单中，不可进入此班级,当前ip:" + ip);
        }
        //名单校验
        Long userId = SecurityUser.getUserId();

        //班级名单校验 + 签到
        LessonEntity entity = lessonService.getById(lessonId);
        if (!lessonAttendLogService.attendance(userId, entity)) {
            return Result.error("不在该课堂中，不可进入此班级");
        }
        return Result.ok();
    }

    @SneakyThrows
    @Override
    public Result<Object> attendExam(Long examId) {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        assert request != null;
        String ip = IpUtils.getIpAddr(request);
        //ip校验
        if (!examIPService.ipJudge(examId, ip)) {
            return Result.error("不在ip白名单中，不可进入此考试,当前ip:" + ip);
        }
        Long userId = SecurityUser.getUserId();
        //考试时间+名单校验
        if (!examAttendLogService.attendance(examId, userId)) {
            throw new ServerException("不在名单中，不可进入此考试");
        }
        ExamPaperVo vo = getStuExamInfo(examId, userId);
        return Result.ok(vo);

    }

    @Override
    public ExamPaperVo getStuExamInfo(Long examId, Long userId) {

        ExamPaperVo vo = (ExamPaperVo) redisUtils.get(RedisKeys.getStuExam(examId, userId));

        if (vo == null) {
            vo = new ExamPaperVo();
            ExamAttendLogVO attendLogVO = examAttendLogService.getUserExamAttend(examId);
            //领取新试卷开始考试
            //需要优化
            List<ExamProblemEntity> list = examProblemService.list(examId);
            //打乱顺序
//            Collections.shuffle(list);
            vo.setPaperProblem(list);
            vo.setProblemIndex(1);
            //考试结束时间
            vo.setAttendLogVO(attendLogVO);
            //多5s前端响应时间
            Long time = vo.getAttendLogVO().getFinishExamTime().getTime() - System.currentTimeMillis() + 5000L;
            redisUtils.set(RedisKeys.getStuExam(examId, userId), vo, time / 1000);
        }
        return vo;
    }



}
