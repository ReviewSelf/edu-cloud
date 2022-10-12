package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

import java.util.Date;

/**
* 教学评价查询
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-08
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "教学评价查询")
public class TeachEvaluateQuery extends Query {

    @Schema(description = "评价人名字")
    private String evaluateUserName;

    @Schema(description = "被评价人名字")
    private String evaluatedUserName;

    @Schema(description = "班级名字")
    private String className;

    @Schema(description = "评价来源")
    private Integer type;

}