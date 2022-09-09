package net.edu.module.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Data
@Configuration
public class LocalStorageProperties {



    /**
     * 本地存储路径
     */
    @Value("${storage.local.path}")
    private String path;

    @Value("${storage.config.domain}")
    private String domain;
    /**
     * 资源起始路径
     */
    @Value("${storage.config.prefix}")
    private String url;
}
