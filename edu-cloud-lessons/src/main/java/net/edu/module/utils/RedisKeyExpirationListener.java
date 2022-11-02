package net.edu.module.utils;

import lombok.extern.slf4j.Slf4j;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.module.service.ExamAttendLogService;
import net.edu.module.service.LessonService;
import net.edu.module.vo.ExamAttendLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/15 11:29
 * @Version: 1.0
 * @Description:
 */
@Component
@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
    @Autowired
    RedisUtils redisUtils;

    @Autowired
    ExamAttendLogService examAttendLogService;

    @Autowired
    LessonService lessonService;


    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 考试 过期key
        String expiredKey = message.toString();
        if (expiredKey.contains(RedisKeys.getStuExam(null, null))) {
            //监听到考试过期
            List<String> list = Arrays.asList(expiredKey.split(":"));
            //exam:user:59:10000考试完成
            log.info("{}考试完成", list.toString());
            //通过userId 和 examId 更新用户考试状态
            examAttendLogService.updateExamStatus(2,
                    Long.valueOf(list.get(2)),Long.valueOf(list.get(3)),new Date());
        }
        else if(expiredKey.contains(RedisKeys.getHomeWorkKey(null))){
            //作业过期
            List<String> list = Arrays.asList(expiredKey.split(":"));
            log.info("{}作业截止", list.toString());
            lessonService.closeLessonHomeWork(Long.valueOf(list.get(2)));
        }
        else if(expiredKey.contains(RedisKeys.getHomeworkEndKey(null))) {
            //作业截止前24小时
            List<String> list = Arrays.asList(expiredKey.split(":"));
            log.info("{}作业截止前24小时" , list.toString());
            lessonService.sendHomeworkEnd(Long.valueOf(list.get(2)));
        }
    }

    public RedisKeyExpirationListener(RedisMessageListenerContainer redisMessageListenerContainer) {
        super(redisMessageListenerContainer);
    }
}
