//package net.edu.framework.common.config;
//
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
///**
// * @Author: 马佳浩
// * @Date: 2022/9/4 1:19
// * @Version: 1.0
// * @Description:
// */
//@Component
//public class RabbitMqAdminConfig {
//    @Bean
//    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
//        // 只有设置为 true，spring 才会加载 RabbitAdmin 这个类
//        rabbitAdmin.setAutoStartup(true);
//        return rabbitAdmin;
//    }
//
//}
