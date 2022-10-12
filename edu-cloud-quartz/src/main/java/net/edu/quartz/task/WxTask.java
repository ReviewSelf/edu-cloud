package net.edu.quartz.task;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.edu.module.api.EduWxApi;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class WxTask {


    private final EduWxApi eduWxApi;

    public void getToken(String str){
        eduWxApi.getAccessToken();
        log.debug("执行了");
    }

    public void sendTemplateMessage(String str){
        eduWxApi.sendTemplateMessage();
        log.debug("执行了sendTemplateMessage");
    }
}
