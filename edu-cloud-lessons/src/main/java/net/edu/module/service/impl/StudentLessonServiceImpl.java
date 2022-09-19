package net.edu.module.service.impl;


import cn.hutool.core.collection.CollectionUtil;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.HttpContextUtils;
import net.edu.framework.common.utils.IpUtils;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.entity.LessonAttendLogEntity;
import net.edu.module.query.LessonAttendLogQuery;
import net.edu.module.query.LessonIPQuery;
import net.edu.module.service.LessonAttendLogService;
import net.edu.module.service.LessonIPService;
import net.edu.module.service.StudentLessonService;
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

    @Override
    public Result attendLesson(Long lessonId) {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        assert request != null;
        String ip = IpUtils.getIpAddr(request);
        //获取ip白名单
        List<LessonIPVO> ipList=lessonIPService.list(new LessonIPQuery(lessonId));

        //白名单校验
        if(!CollectionUtil.isEmpty(ipList)){
            for (LessonIPVO item:ipList){
                if(!IpUtils.ipExistsInRange(ip,item.getIpRange())){
                    return  Result.error("不在ip白名单中，不可进入此班级");
                }
            }
        }

        Long userId = SecurityUser.getUserId();
        // List<LessonAttendLogVO> userList=lessonAttendLogService.list(new LessonAttendLogQuery(lessonId));
        //班级名单校验 + 签到
        return lessonAttendLogService.attendance(userId,lessonId);
    }


}
