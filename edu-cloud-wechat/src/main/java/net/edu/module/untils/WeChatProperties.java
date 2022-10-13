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
    public String app_id;

    @Value("${wechat.appSecret}")
    public String app_secret;

    @Value("${wechat.mini.appid}")
    public String mini_app_id;

    @Value("${wechat.mini.appSecret}")
    public String mini_app_secret;


    @Value("${wechat.judgeToken}")
    public String judge_token;

    //公众号菜单入口
    @Value("${wechat.redirectClassUrl}")
    public String redirect_class_url;

    @Value("${wechat.accountUrl}")
    public String account_url;


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
        APP_ID = app_id;
        APP_SECRET = app_secret;
        MINI_APP_ID = mini_app_id;
        MINI_APP_SECRET = mini_app_secret;
        JUDGE_TOKEN = judge_token;
        REDIRECT_CLASS_URL = redirect_class_url;
        ACCOUNT_URL = account_url;
    }
}
