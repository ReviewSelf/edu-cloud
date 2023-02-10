package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

/**
* 学生班级记录查询
*
* @author sqw 
* @since 1.0.0 2023-02-08
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "学生班级记录查询")
public class TeachClassRecordQuery extends Query {
    @Schema(description = "1代表插班，2代表换班，3代表退班")
    private Integer type;

    @Schema(description = "学生姓名")
    private String studentName;

}