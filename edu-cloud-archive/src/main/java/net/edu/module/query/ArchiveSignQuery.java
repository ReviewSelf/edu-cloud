package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;


import java.util.Date;

/**
* 平时记录查询
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-23
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "平时记录查询")
public class ArchiveSignQuery extends Query {
    @Schema(description = "记分册编号")
    private Long bookId;
}
