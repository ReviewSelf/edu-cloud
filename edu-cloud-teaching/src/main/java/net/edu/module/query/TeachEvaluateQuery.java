package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

import java.util.Date;

/**
* 教学评价查询
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-08
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "教学评价查询")
public class TeachEvaluateQuery extends Query {

    @Schema(description = "学生姓名")
    private String name;

    @Schema(description = "课堂名称")
    private String lessonName;

    @Schema(description = "班级名称")
    private String className;



}