package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

import java.util.Date;

/**
* 考核点查询
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-26
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "考核点查询")
public class ArchiveAssessQuery extends Query {
    @Schema(description = "考核点编号")
    private Long id;

    @Schema(description = "考核点名称")
    private String name;

    @Schema(description = "年级")
    private String grade;

    @Schema(description = "指标点名称")
    private String targetName;

    @Schema(description = "一级课程名称")
    private String courseName;

}
