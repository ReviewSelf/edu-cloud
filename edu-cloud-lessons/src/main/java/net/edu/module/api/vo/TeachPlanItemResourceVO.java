package net.edu.module.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Schema(description = "教学日历资源表")
public class TeachPlanItemResourceVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "日历资源id")
    private Long id;

    @Schema(description = "日历id")
    private Long itemId;

    @Schema(description = "日历资源名称")
    private String name;

    @NotBlank(message = "文件不能为空")
    @Schema(description = "日历资源路径")
    private String filePath;


    @Schema(description = "日历资源备注")
    private String remark;


    @Schema(description = "日历资源类型")
    private Integer fileType;
}
