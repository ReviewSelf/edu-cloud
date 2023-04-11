package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "宣传推广人查询")
public class PublicityQuery extends Query {
    private String beginTime;

    private String endTime;

    private Long id;
}
