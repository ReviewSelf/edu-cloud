package net.edu.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 账号登录
 *
 * @author 沈凯文
 */
@Data
@Schema(description = "微信登陆")
public class SysWeChatLoginVO {
    private static final long serialVersionUID = 1L;

    @Schema(description = "unionId")
    private String unionId;
}
