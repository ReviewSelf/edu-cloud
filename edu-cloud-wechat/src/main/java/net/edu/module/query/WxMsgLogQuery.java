package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

/**
* 消息推送查询
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-11
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "消息推送查询")
public class WxMsgLogQuery extends Query {
    @Schema(description = "面向用户")
    private Long userId;

    @Schema(description = "是否已推")
    private Integer isPush;

    @Schema(description = "消息类型")
    private Integer type;

}
