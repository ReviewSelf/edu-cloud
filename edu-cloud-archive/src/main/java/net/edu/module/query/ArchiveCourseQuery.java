package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;


import java.util.Date;

/**
* 能力课程查询
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-25
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "能力课程查询")
public class ArchiveCourseQuery extends Query {
    @Schema(description = "名称")
    private String name;

    @Schema(description = "课程类别")
    private String courseCategory;

    @Schema(description = "课程类型")
    private String type;

    @Schema(description = "面向年级")
    private String grade;
}