package net.edu.module.service.impl;


import cn.hutool.core.collection.CollectionUtil;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.HttpContextUtils;
import net.edu.framework.common.utils.IpUtils;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.entity.LessonAttendLogEntity;
import net.edu.module.entity.LessonEntity;
import net.edu.module.query.LessonAttendLogQuery;
import net.edu.module.query.LessonIPQuery;
import net.edu.module.service.*;
import net.edu.module.vo.LessonAttendLogVO;
import net.edu.module.vo.LessonIPVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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



    @Override
    public Result<String> attendLesson(Long lessonId) {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        assert request != null;
        String ip = IpUtils.getIpAddr(request);
        //ip校验
       if(!lessonIPService.ipJudge(lessonId,ip)){
          return Result.error("不在ip白名单中，不可进入此班级,当前ip:"+ip);
       }
       //名单校验
        Long userId = SecurityUser.getUserId();

        //班级名单校验 + 签到
        LessonEntity entity=lessonService.getById(lessonId);
        if(!lessonAttendLogService.attendance(userId,entity)){
            return Result.error("不在该课堂中，不可进入此班级");
        }
        return Result.ok();
    }

    @Override
    public Result<String> attendExam(Long examId) {
        //自身校验




        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        assert request != null;
        String ip = IpUtils.getIpAddr(request);
        //ip校验
        if(!examIPService.ipJudge(examId,ip)){
            return Result.error("不在ip白名单中，不可进入此考试,当前ip:"+ip);
        }
        //名单校验
        Long userId = SecurityUser.getUserId();

        //班级名单校验 + 考试时间
//        LessonEntity entity=lessonService.getById(lessonId);
//        if(!lessonAttendLogService.attendance(userId,entity)){
//            return Result.error("不在该课堂中，不可进入此班级");
//        }
        return Result.ok();

    }


}
