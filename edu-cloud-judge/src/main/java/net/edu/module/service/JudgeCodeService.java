package net.edu.module.service;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import net.edu.framework.common.mq.QueueName;
import net.edu.module.entity.CodeRecordEntity;
import org.springframework.amqp.rabbit.core.ChannelCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/4 1:10
 * @Version: 1.0
 * @Description:
 */
@Service
public class JudgeCodeService {

    @Autowired
    RabbitTemplate rabbitTemplate;









    /**
     * 获取对应队列的数量;
     *
     * @param queueName
     * @return
     */
    public int getMessageCount(String queueName) {
        AMQP.Queue.DeclareOk declareOk = rabbitTemplate.execute(new ChannelCallback<AMQP.Queue.DeclareOk>() {
            public AMQP.Queue.DeclareOk doInRabbit(Channel channel) throws Exception {
                return channel.queueDeclarePassive(queueName);
            }
        });
        return declareOk.getMessageCount();
    }

    public int judgeBefore(CodeRecordEntity codeRecordEntity) {
        //插入记录


        //当前列队数量，0表示正在判题，1表示1人等待依次类推；
        int num=getMessageCount(QueueName.JUDGE_QUEUE);
        //提交到mq



        return num;
    }


    public void judge(CodeRecordEntity codeRecordEntity) {
        //获取测试样例

        //遍历样例判题

        //写入样例运行结果

        //生成/更新运行结果

    }


    public void judgeAfter(CodeRecordEntity codeRecordEntity) {

        //更新用户答题次数/准确次数


        //更新题目回答次数/正确次数

    }
}
