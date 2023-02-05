package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

/**
* 沟通记录表查询
*
* @author sqw 
* @since 1.0.0 2023-02-05
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "沟通记录表查询")
public class TeachCommunicateRecordQuery extends Query {
}