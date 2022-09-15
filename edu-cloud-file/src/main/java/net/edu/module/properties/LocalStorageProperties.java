package net.edu.module.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 *
 */
@Data
@Configuration
@Component
public class LocalStorageProperties {



    /**
     * 本地存储路径
     */
    @Value("${storage.local.path}")
    private String path="E://upload";

    @Value("${storage.config.domain}")
    private String domain="http://127.0.0.1:8080/file";
    /**
     * 资源起始路径
     */
    @Value("${storage.config.prefix}")
    private String url="upload";
}
