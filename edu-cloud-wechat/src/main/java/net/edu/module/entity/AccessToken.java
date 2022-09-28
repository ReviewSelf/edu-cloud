package net.edu.module.entity;

import lombok.Data;

/**
 * @author weng
 * @date 2022/9/24 - 11:46
 **/
@Data
public class AccessToken {
    // 获取到的凭证

    private String token;

    // 凭证有效时间，单位：秒

    private int expiresIn;

    public static final String APPID = "wx5d0fc93575b299a8";
}
