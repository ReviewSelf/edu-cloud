package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

/**
* 1查询
*
* @author 马佳浩 
* @since 1.0.0 2022-09-15
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "1查询")
public class LessonIPQuery extends Query {
}