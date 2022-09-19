package net.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/15 9:28
 * @Version: 1.0
 * @Description:
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class LessonsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LessonsApplication.class, args);
    }
}
