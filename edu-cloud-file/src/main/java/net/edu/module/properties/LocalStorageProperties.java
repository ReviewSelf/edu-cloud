package net.edu.module.properties;

import lombok.Data;

/**
 *
 */
@Data
public class LocalStorageProperties {

    public LocalStorageProperties() {
        this.path= FileFinal.UPLOAD_FILE_PATH;
        this.domain=FileFinal.UPLOAD_FILE_DOMAIN;
        this.url=FileFinal.UPLOAD_FILE_URL;
    }

    /**
     * 本地存储路径
     */
    private String path;

    private String domain;
    /**
     * 资源起始路径
     */
    private String url;
}
