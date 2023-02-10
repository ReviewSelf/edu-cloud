package net.edu.module.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;
import net.edu.framework.common.utils.DateUtils;

import java.util.Date;

/**
* 沟通记录表查询
*
* @author sqw 
* @since 1.0.0 2023-02-05
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "沟通记录表查询")
public class TeachCommunicateRecordQuery extends Query {
    @Schema(description = "学生id")
    private Long studentId;

    @Schema(description = "学生姓名")
    private String studentName;

    @Schema(description = "沟通时间")
    private String time;
}