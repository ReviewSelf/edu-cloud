package net.edu.framework.common.utils;

/**
 * @Author: 马佳浩
 * @Date: 2022/11/9 9:54
 * @Version: 1.0
 * @Description:
 */
public class UserTokenContext {
    /**
     * 当前线程的TOKEN副本
     */
    private static ThreadLocal<String> userToken = new InheritableThreadLocal<String>();

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
