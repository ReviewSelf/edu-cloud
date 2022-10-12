package net.edu.quartz.task;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.edu.module.api.EduLessonApi;
import org.springframework.stereotype.Component;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/8 14:32
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Component
@AllArgsConstructor
public class LessonTask {

    private final EduLessonApi eduLessonApi;

    public void homeWorkDeadline(String str){
        eduLessonApi.homeWorkDeadline();
        log.debug("回家作业截止操作执行了");
    }
}
