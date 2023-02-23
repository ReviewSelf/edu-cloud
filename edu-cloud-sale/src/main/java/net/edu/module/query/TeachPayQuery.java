package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

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

    @Schema(description = "学生姓名")
    private String realName;

    @Schema(description = "经手人")
    private String handler;

    @Schema(description = "销售老师")
    private String saleName;

    @Schema(description = "状态")
    private Integer status;
}