package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

/**
 * @Description: TODO
 * @author: sl
 * @date: 2022年10月08日 19:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "lesson题目查询")
public class HomeWorkQuery extends Query {

    @Schema(description = "学生id")
    private String StudentId;
}
