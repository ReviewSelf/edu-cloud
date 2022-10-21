package net.edu.framework.security.utils;

import cn.hutool.core.lang.UUID;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Token 工具类
 *
 * @author 阿沐 babamu@126.com
 */
public class TokenUtils {

    /**
     * 生成 AccessToken
     */
    public static String generator(String account) {
        if(account==null){
            return UUID.fastUUID().toString(true);
        }
        return account+UUID.fastUUID().toString(true);
    }

    /**
     * 获取 AccessToken
     */
    public static String getAccessToken(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization");
        if (StringUtils.isBlank(accessToken)) {
            accessToken = request.getParameter("access_token");
        }

        return accessToken;
    }
}
