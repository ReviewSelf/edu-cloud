package net.edu.module.untils;


import net.edu.module.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@EnableScheduling
public class AsyncTasks {
    @Autowired
    private WxService wxService;

    @Async("basicExecutor") //异步方法；这些方法将在执行的时候，将会在独立的线程中被执行，调用者无需等待它的完成，即可继续其他的操作，相当于加入线程池
    @Scheduled(cron = "0 0 0/1 * * ?")  //每天凌晨7点执行一次
    public void getAccessToken() {
        wxService.getAccessToken();
    }

}

