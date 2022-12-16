package net.edu.lessonsocket.config;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

/**
 * @author 马佳浩
 * @date 2022/11/30 14:09:20
 * @description
 */
@Data
@Component
public class LessonNettyConfig implements InitializingBean {
    /**
     * netty监听的端口
     */
    @Value("${netty.lesson.port}")
    private int port;
    /**
     * websocket访问路径
     */
    @Value("${netty.lesson.path}")
    private String path;

    public static int PORT;

    public static String PATH;


    @Override
    public void afterPropertiesSet() throws Exception {
        PORT=port;
        PATH=path;
    }
}
