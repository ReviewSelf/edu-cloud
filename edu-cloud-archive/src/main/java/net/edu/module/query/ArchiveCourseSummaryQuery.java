package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;


/**
* 课程总结查询
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-14
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "课程总结查询")
public class ArchiveCourseSummaryQuery extends Query {
}