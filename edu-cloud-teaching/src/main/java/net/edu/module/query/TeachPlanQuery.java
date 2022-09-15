package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

import java.util.Date;

/**
* 课堂签到表查询
*
**/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "教学计划表查询")
public class TeachPlanQuery extends Query {
    @Schema(description = "名称")
    private String name;

    @Schema(description = "难度")
    private Integer difficulty;

    @Schema(description = "课次")
    private Integer lessonNum;

    @Schema(description = "状态")
    private Integer status;

}