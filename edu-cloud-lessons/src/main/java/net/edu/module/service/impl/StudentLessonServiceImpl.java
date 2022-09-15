package net.edu.module.service.impl;

import net.edu.framework.common.exception.ServerException;
import net.edu.framework.common.utils.AddressUtils;
import net.edu.framework.common.utils.HttpContextUtils;
import net.edu.framework.common.utils.IpUtils;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.user.SecurityUser;
import net.edu.framework.security.user.UserDetail;
import net.edu.module.service.StudentLessonService;
import org.springframework.http.HttpHeaders;
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
public class StudentLessonServiceImpl implements StudentLessonService {


    @Override
    public Result attendLesson() {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        assert request != null;
        String ip = IpUtils.getIpAddr(request);
        List<String> ipList=null;
        //获取ip白名单

        //白名单校验
        if(ipList!=null){
            for (String ipRange:ipList){
                if(!IpUtils.ipExistsInRange(ip,ipRange)){
                    return  Result.error("不在ip白名单中，不可进入此班级");
                }
            }
        }
        Long userId = SecurityUser.getUserId();

        //班级名单校验
        if(false){
            return  Result.error("不在该班级中，不可进入此班级");
        }

        // 签到



        return Result.ok();
    }


}
