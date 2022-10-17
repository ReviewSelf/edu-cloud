package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

/**
* 课堂练习表查询
*
* @author 马佳浩 
* @since 1.0.0 2022-09-15
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "课堂练习表查询")
public class ExamProblemQuery extends Query {

    @Schema(description = "课堂ID")
    private Long lessonId;

    @Schema(description = "练习类型")
    private Integer type;


}