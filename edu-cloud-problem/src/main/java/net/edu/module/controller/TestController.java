package net.edu.module.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 马佳浩
 * @Date: 2022/11/4 12:54
 * @Version: 1.0
 * @Description:
 */
@RestController
@Tag(name="能力纬度图")
@AllArgsConstructor
@Slf4j
public class TestController {


    @GetMapping("/test")
    public void test(HttpServletRequest request) {
        RequestAttributes attr = RequestContextHolder.getRequestAttributes();
        log.info("父线程:RequestAttributes:{}", attr);
        RequestContextHolder.setRequestAttributes(attr, true);
        log.info("父线程:SpringMVC:request:{}",request);
        log.info("父线程:x-auth-token:{}",request.getHeader("x-auth-token"));
        ServletRequestAttributes attr1 = (ServletRequestAttributes) attr;
        HttpServletRequest request1 = attr1.getRequest();
        log.info("父线程:request:{}",request1);
        new Thread(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    RequestAttributes childAttr = RequestContextHolder.getRequestAttributes();
                    log.info("子线程:RequestAttributes:{}",childAttr);
                    ServletRequestAttributes childServletRequestAttr = (ServletRequestAttributes) childAttr;
                    HttpServletRequest childRequest = childServletRequestAttr.getRequest();
                    log.info("子线程:childRequest:{}",childRequest);
                    String childToken = childRequest.getHeader("x-auth-token");
                    log.info("子线程:x-auth-token:{}",childToken);
                }).start();
    }
}
