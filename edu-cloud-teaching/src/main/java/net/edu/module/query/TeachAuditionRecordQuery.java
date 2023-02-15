package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

/**
* 教学试听安排查询
*
* @author sqw 
* @since 1.0.0 2023-02-13
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "教学试听安排查询")
public class TeachAuditionRecordQuery extends Query {
    @Schema(description = "学生名称")
    private String studentName;

    @Schema(description = "课堂名称")
    private String lessonName;

    @Schema(description = "是否已安排试听,0未安排，1安排")
    private Integer status;

}