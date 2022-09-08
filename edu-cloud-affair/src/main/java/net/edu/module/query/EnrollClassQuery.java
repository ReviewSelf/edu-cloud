package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

import java.util.Date;

/**
* 班级发布查询
*
* @author 翁瑞辰 
* @since  2022-09-06
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "班级发布查询")
public class EnrollClassQuery extends Query {
    @Schema(description = "假班级id")
    private Long id;

    @Schema(description = "班级名称")
    private String className;

}