package net.edu.quartz.task;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.edu.module.api.EduTeachApi;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class TeachTask {

    private final EduTeachApi eduTeachApi;

    public void statisticsHomeInfo(String str){
        eduTeachApi.statisticsHomeInfo();
        log.debug("回家作业截止操作执行了");
    }
}
