package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

/**
* 课时流水表查询
*
* @author sqw 
* @since 1.0.0 2023-02-15
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "课时流水表查询")
public class ClassHoursFlowRecordQuery extends Query {
    @Schema(description = "班级id")
    private Long classId;

    @Schema(description = "场景：0是退班、1是插班、3是教务续费、4是销售充值")
    private Integer scene;

    @Schema(description = "状态：0是退费、1是充值、2是扣费")
    private Integer status;

}