package net.edu.module.consumer;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import net.edu.framework.common.mq.QueueName;
import net.edu.module.service.JudgeService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
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
    @RabbitListener(queues = QueueName.JUDGE_QUEUE  ,concurrency = "3")
    @RabbitHandler
    public void listenerJudge(String msg, Channel channel, Message message) {
        try {
            judgeService.judgeCode(Long.valueOf(msg));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            throw new RuntimeException(e);
        }
    }


}
