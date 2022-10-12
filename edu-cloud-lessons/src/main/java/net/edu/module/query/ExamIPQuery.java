package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* 1查询
*
* @author 马佳浩 
* @since 1.0.0 2022-09-15
*/
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Schema(description = "IP查询")
public class ExamIPQuery {
    private Long examId;
}