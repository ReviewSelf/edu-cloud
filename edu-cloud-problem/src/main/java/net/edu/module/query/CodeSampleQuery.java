package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;


/**
* 测试样例表查询
*
* @author sqw 
* @since 1.0.0 2022-09-07
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "测试样例表查询")
public class CodeSampleQuery extends Query {
    private Integer problemId;
    private Long id;

}