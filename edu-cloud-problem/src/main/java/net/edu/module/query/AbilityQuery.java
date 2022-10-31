package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

/**
* 能力纬度图查询
*
* @author sqw 
* @since 1.0.0 2022-10-27
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "能力纬度图查询")
public class AbilityQuery extends Query {
    @Schema(description = "能力图名称")
    private String name;

}