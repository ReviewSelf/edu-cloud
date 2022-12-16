package net.edu;

import net.edu.lessonsocket.config.LessonNettyConfig;
import net.edu.lessonsocket.service.LessonWebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/15 9:28
 * @Version: 1.0
 * @Description:
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableAsync
public class LessonsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LessonsApplication.class, args);
    }

    @Autowired
    private LessonWebSocketServer lessonWebSocketServer;

    @Override
    public void run(String... args) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                lessonWebSocketServer.start(LessonNettyConfig.PORT);
            }
        }).start();
    }
}


