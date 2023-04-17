package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

/**
* 消课管理查询
*
* @author sqw 
* @since 1.0.0 2023-03-04
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "消课管理查询")
public class TeachDestroyedLessonRecordQuery extends Query {
    private String studentName;
    private Integer type;
}