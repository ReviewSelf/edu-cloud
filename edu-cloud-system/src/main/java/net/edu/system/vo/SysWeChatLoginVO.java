package net.edu.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/11 13:16
 * @Version: 1.0
 * @Description:
 */
@Data
@Schema(description = "unionId登录")
public class SysWeChatLoginVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "unionId")
    private String unionId;


}
