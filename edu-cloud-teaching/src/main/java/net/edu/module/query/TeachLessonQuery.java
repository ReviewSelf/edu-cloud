package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;


import java.util.Date;

/**
* 课程表查询
*
* @author 翁瑞辰 babamu@126.com
* @since 1.0.0 2022-09-09
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "课程表查询")
public class TeachLessonQuery extends Query {
}