package net.edu.module.consumer;

import lombok.SneakyThrows;
import net.edu.framework.common.mq.QueueName;
import net.edu.module.service.JudgeService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/3 23:33
 * @Version: 1.0
 * @Description:
 */
@Component
public class JudgeConsumer {

    @Autowired
    JudgeService judgeService;


    @SneakyThrows
    @RabbitListener(queues = QueueName.JUDGE_QUEUE, concurrency = "3")
    @RabbitHandler
    public void listenerJudge(String recordId) {
        System.out.println(new Date() + recordId);
        judgeService.judgeCode(Long.valueOf(recordId));
    }


}
