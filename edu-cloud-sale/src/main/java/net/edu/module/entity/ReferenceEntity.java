package net.edu.module.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ReferenceEntity {
    @Schema(description = "推荐人id")
    private String reference;
    @Schema(description = "推荐人学号")
    private String stuNum;
    @Schema(description = "推荐人姓名")
    private String name;
    @Schema(description = "推荐人推荐成功总数")
    private int total;
}
