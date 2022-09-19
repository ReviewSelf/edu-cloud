package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

import java.util.Date;

/**
 * 课堂签到表查询
 *
 * @author 马佳浩
 * @since 1.0.0 2022-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "课堂签到表查询")
public class LessonAttendLogQuery extends Query {
}