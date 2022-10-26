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
    @Value("${wechat.classUrl}")
    public String classUrl;

    @Value("${wechat.accountUrl}")
    public String accountUrl;

    @Value("${wechat.feedbackUrl}")
    public String feedbackUrl;

    @Value("${wechat.evaluationUrl}")
    public String evaluationUrl;

    public static String APP_ID;

    public static String APP_SECRET;

    public static String MINI_APP_ID;

    public static String MINI_APP_SECRET;


    public static String JUDGE_TOKEN;

    //公众号菜单入口

    public static String CLASS_URL;

    public static String ACCOUNT_URL;

    public static String FEEDBACK_URL;

    public static String EVALUATION_Url;


    @Override
    public void afterPropertiesSet() throws Exception {
        APP_ID = appId;
        APP_SECRET = appSecret;
        MINI_APP_ID = miniAppId;
        MINI_APP_SECRET = miniAppSecret;
        JUDGE_TOKEN = judgeToken;
        CLASS_URL = classUrl;
        ACCOUNT_URL = accountUrl;
        FEEDBACK_URL = feedbackUrl;
        EVALUATION_Url = evaluationUrl;
    }
}
