package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
* 问题资源表查询
*
* @author 周建超 
* @since 1.0.0 2022-09-20
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "问题资源表查询")
public class ProblemResourceQuery  {
    @Schema(description = "问题ID")
    private Long problemId;

    @Schema(description = "问题类型 1选择 2填空 3代码")
    private Integer problemType;

}