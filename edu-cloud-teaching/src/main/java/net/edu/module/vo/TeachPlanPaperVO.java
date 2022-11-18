package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "教学计划关联试卷表")
public class TeachPlanPaperVO {
    private Long paperId;

    private Long planId;

    private String paperName;

    private String remark;

    private Integer problemNum;

    private Integer score;

    private String description;
}
