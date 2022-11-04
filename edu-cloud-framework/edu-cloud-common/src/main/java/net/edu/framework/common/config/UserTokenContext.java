package net.edu.framework.common.config;

/**
 * @Author: 马佳浩
 * @Date: 2022/11/4 13:20
 * @Version: 1.0
 * @Description:
 */
public class UserTokenContext {
    private static ThreadLocal<String> userToken = new ThreadLocal<String>();

    public UserTokenContext() {
    }

    public static String getToken(){
        return userToken.get();
    }

    public static void setToken(String token){
        userToken.set(token);
    }

    public static void remove() {
        userToken.remove();
    }
}
