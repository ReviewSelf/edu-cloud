package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

/**
* 代码题库表查询
*
* @author 马佳浩 
* @since 1.0.0 2022-09-05
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "代码题库表查询")
public class CodeProblemQuery extends Query {
    @Schema(description = "名称")
    private String name;

    @Schema(description = "难度")
    private Integer difficulty;

    @Schema(description = "知识点id")
    private Long kpId;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "典型问题")
    private Integer isTypical;
}