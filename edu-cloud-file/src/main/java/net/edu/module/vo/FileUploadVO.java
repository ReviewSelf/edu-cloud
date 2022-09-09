package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
@Schema(description = "文件上传")
public class FileUploadVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "文件名称")
    private String name;

    @Schema(description = "文件地址")
    private String url;

    @Schema(description = "文件大小(字节/B)")
    private Long size;
}
