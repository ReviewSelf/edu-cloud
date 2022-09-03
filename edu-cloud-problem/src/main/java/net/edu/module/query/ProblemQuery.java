package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "问题列表查询")
public class ProblemQuery extends Query {
    @Schema(description = "问题标题")
    private String name;
    @Schema(description = "问题难度")
    private Integer difficulty;
    @Schema(description = "问题状态")
    private Integer state;
    @Schema(description = "知识点类别")
    private Integer kpId;
}
