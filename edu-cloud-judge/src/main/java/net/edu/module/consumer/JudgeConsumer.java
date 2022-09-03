package net.edu.module.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import net.edu.framework.common.mq.QueueName;
import net.edu.module.entity.CodeRecordEntity;
import net.edu.module.service.JudgeCodeService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.ChannelCallback;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Map;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/3 23:33
 * @Version: 1.0
 * @Description:
 */
@Component
public class JudgeConsumer {

    @Autowired
    JudgeCodeService judgeCodeService;


    @SneakyThrows
    @RabbitListener(queues= QueueName.JUDGE_QUEUE,concurrency = "3")
    @RabbitHandler
    public void listenerJudge(Channel channel, Message message){
        System.out.println(new Date()+new String(message.getBody(), Charset.defaultCharset()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
       judgeCodeService.judge(new CodeRecordEntity());
       judgeCodeService.judgeBefore(new CodeRecordEntity());
    }





}
