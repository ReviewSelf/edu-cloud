package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;


import java.util.Date;

/**
* XinXiHeShi查询
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-09-05
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "XinXiHeShi查询")
public class EnrollUserQuery extends Query {
    @Schema(description = "真实姓名")
    private String name;

    @Schema(description = "状态")
    private String status;
}