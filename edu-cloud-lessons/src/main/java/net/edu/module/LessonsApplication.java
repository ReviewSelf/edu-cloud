package net.edu.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/15 9:28
 * @Version: 1.0
 * @Description:
 */
@EnableDiscoveryClient
@SpringBootApplication
public class LessonsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LessonsApplication.class, args);
    }
}
