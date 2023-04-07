package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "按教师统计")
public class StatisticsTeacherVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "新增意向人数")
    private Integer intention;

    @Schema(description = "新增沟通人数")
    private Integer communication;

    @Schema(description = "新增试听人数")
    private Integer audition;

    @Schema(description = "新增成交人数")
    private Integer deal;

    @Schema(description = "季度成交金额")
    private Long amount;
}
