package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;


import java.util.Date;

/**
* 流水账管理查询
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-09-16
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "流水账管理查询")
public class TeachPayQuery extends Query {
}