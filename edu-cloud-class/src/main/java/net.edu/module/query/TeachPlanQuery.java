package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

/**
* 教学计划表查询
*
* @author 阿沐
* @since 1.0.0 2022-09-08
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "教学计划表查询")
public class TeachPlanQuery extends Query {
    @Schema(description = "名称")
    private String name;

}
