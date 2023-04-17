package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

/**
* 课时流水管理查询
*
* @author sqw 
* @since  2023-03-06
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "课时流水管理查询")
public class TeachClassHoursFlowRecordQuery extends Query {
    @Schema(description = "用户名称")
    private String userName;

    @Schema(description = "类型：0是退课时、1是加课时、2是扣课时")
    private Integer type;

}