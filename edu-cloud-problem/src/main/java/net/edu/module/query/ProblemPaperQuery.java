package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

/**
 * 问题卷表查询
 *
 * @author 樊磊
 * @since 1.0.0 2022-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "问题卷表查询")
public class ProblemPaperQuery extends Query {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "难度")
    private Integer difficulty;


    @Schema(description = "知识点id")
    private Long kpId;
}