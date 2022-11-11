package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

import java.util.Date;

/**
* target查询
*
* @author qxd babamu@126.com
* @since 1.0.0 2022-10-24
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "target查询")
public class ArchiveTargetQuery extends Query {
    @Schema(description = "名称")
    private String name;

    @Schema(description = "毕业要求名称")
    private String graduateTitle;

    @Schema(description = "年级")
    private Integer grade;

    @Schema(description = "课程名称Id")
    private Long courseId;

}
