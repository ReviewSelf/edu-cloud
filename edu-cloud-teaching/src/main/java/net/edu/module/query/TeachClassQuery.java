package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

import java.util.Date;

/**
 * 班级表查询
 *
 * @author wengruichen babamu@126.com
 * @since 1.0.0 2022-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "班级表查询")
public class TeachClassQuery extends Query {
    @Schema(description = "班级名称")
    private String name;

    @Schema(description = "引用教学计划")
    private String planName;

    @Schema(description = "任课老师")
    private String Teacher2Name;

}
