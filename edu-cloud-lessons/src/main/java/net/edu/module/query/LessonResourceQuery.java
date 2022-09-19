package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

import java.util.Date;

/**
* 教学日历资源表查询
*
* @author 马佳浩 babamu@126.com
* @since 1.0.0 2022-09-15
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "教学日历资源表查询")
public class LessonResourceQuery extends Query {
}