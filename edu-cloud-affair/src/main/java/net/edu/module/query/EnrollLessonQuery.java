package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "试听课程查询")
public class EnrollLessonQuery extends Query {
    @Schema(description = "班级ID")
    private Integer classId;
}
