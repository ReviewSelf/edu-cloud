package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Description: TODO
 * @author: sl
 * @date: 2022年09月16日 16:55
 */
@Data
@Schema(description = "用户重置密码")
public class PasswordVo {
    private String id;
    private String password;
}
