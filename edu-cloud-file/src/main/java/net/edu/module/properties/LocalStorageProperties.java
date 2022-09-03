package net.edu.module.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 */
@Data
public class LocalStorageProperties {
    /**
     * 本地存储路径
     */
    private String path="E:/upload";

    private String domain="http://127.0.0.1:8080/file";
    /**
     * 资源起始路径
     */
    private String url = "upload";
}
