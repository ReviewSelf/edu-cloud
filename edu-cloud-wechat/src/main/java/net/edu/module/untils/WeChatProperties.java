package net.edu.module.untils;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/11 14:17
 * @Version: 1.0
 * @Description:
 */
@Data
@Component
public class WeChatProperties implements InitializingBean {
    public static String TOKEN = "";


    @Value("${wechat.appid}")
    public String appId;

    @Value("${wechat.appSecret}")
    public String appSecret;

    @Value("${wechat.mini.appid}")
    public String miniAppId;

    @Value("${wechat.mini.appSecret}")
    public String miniAppSecret;


    @Value("${wechat.judgeToken}")
    public String judgeToken;

    //公众号菜单入口
    @Value("${wechat.redirectClassUrl}")
    public String redirectClassUrl;

    @Value("${wechat.accountUrl}")
    public String accountUrl;


    public static String APP_ID;

    public static String APP_SECRET;

    public static String MINI_APP_ID;

    public static String MINI_APP_SECRET;


    public static String JUDGE_TOKEN;

    //公众号菜单入口

    public static String REDIRECT_CLASS_URL;

    public static String ACCOUNT_URL;


    @Override
    public void afterPropertiesSet() throws Exception {
        APP_ID = appId;
        APP_SECRET = appSecret;
        MINI_APP_ID = miniAppId;
        MINI_APP_SECRET = miniAppSecret;
        JUDGE_TOKEN = judgeToken;
        REDIRECT_CLASS_URL = redirectClassUrl;
        ACCOUNT_URL = accountUrl;
    }
}
