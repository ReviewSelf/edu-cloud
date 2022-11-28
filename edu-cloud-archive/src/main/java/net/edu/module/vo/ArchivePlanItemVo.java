package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ArchivePlanItemVo {
    @Schema(description = "计划子项")
    private Long id;

    @Schema(description = "教学计划id")
    private Long planId;

    @Schema(description = "周次")
    private Integer weeks;

    @Schema(description = "日期（月/日）")
    private String date;

    @Schema(description = "子项顺序")
    private Integer sort;

    @Schema(description = "时数1")
    private String hours1;

    @Schema(description = "实验、讨论课、习题课等环节名称")
    private String others;

    @Schema(description = "作业")
    private String homework;

    @Schema(description = "时数2")
    private String hours2;

    @Schema(description = "时长（分钟）")
    private Integer duration;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "说明")
    private String description;
}
