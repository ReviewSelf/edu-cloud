package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

/**
* 教学日历表查询
*
* @author sqw 
* @since 1.0.0 2022-09-12
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "教学日历表查询")
public class TeachPlanItemQuery extends Query {
    private Long id; //教学计划id
}