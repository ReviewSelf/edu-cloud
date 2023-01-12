package net.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 马佳浩
 * @date 2023/1/11 22:20:45
 * @description
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class SaleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaleApplication.class,args);
    }
}
