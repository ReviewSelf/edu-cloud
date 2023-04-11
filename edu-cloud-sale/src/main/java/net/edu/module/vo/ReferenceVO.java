package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReferenceVO {
    @Schema(description = "推荐人id")
    private String reference;
    @Schema(description = "推荐人学号")
    private String stuNum;
    @Schema(description = "推荐人姓名")
    private String name;
    @Schema(description = "推荐人推荐成功总数")
    private String mobile;
    @Schema(description = "推荐人推荐成功总数")
    private int total;
    @Schema(description = "推荐人推荐成功总数")
    private String username;

}
