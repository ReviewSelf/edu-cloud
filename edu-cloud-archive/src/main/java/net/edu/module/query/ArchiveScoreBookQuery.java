package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;


/**
* 记分册查询
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-22
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "记分册查询")
public class ArchiveScoreBookQuery extends Query {
}
