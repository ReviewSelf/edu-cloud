package net.edu.module.untils;

import lombok.SneakyThrows;

import java.net.URLEncoder;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/13 18:21
 * @Version: 1.0
 * @Description:
 */
public class WeChatApiUtils {

    public static final String BASE_URL = "https://api.weixin.qq.com";



    public static final String TOKEN_URL = BASE_URL + "/cgi-bin/token?grant_type=client_credential&appid=" + WeChatProperties.APP_ID +  "&secret=" + WeChatProperties.APP_SECRET;

    public static final String MENU_URL = BASE_URL + "/cgi-bin/menu/create?access_token="+WeChatProperties.TOKEN  ;

    public static final String UNION_URL = BASE_URL +"/cgi-bin/user/info?access_token="+WeChatProperties.TOKEN  ;


    //2.通过code换取的是一个特殊的网页授权access_token
    public static final String ACCESS_TOKEN_BASE_URL = BASE_URL +"/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    //3.access_token拥有较短的有效期，当access_token超时后，可以使用refresh_token进行刷新，refresh_token有效期为30天，当refresh_token失效之后，需要用户重新授权。
    public static final String ACCESS_TOKEN_REFRESH_URL = BASE_URL +"sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";

    //4.通过access_token和openid拉取用户信息
    public static final String INFO_BASE_URL = BASE_URL +"https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    //允许的范围
    public static final String SNSAPI_USERINFO = "snsapi_userinfo";


    public static final String GROUP_MESSAGE_URL=BASE_URL +"https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token="+WeChatProperties.TOKEN;



    @SneakyThrows
    public static String getAuthBaseUrl(String redirectUrL, String scope){
        return "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ WeChatProperties.APP_ID
                +"&redirect_uri="+ URLEncoder.encode(redirectUrL, "UTF-8")+"&response_type=code&" +
                "scope="+scope+"#wechat_redirect";
    }

    public static String getUnionUrl( String openId){
        return BASE_URL +"/cgi-bin/user/info?access_token="+WeChatProperties.TOKEN
                +"&openid="+openId;
    }

    @SneakyThrows
    public static String getAccessTokenBaseUrl(String code){
        return BASE_URL +"/sns/oauth2/access_token?appid=" +WeChatProperties.APP_ID
                +"&secret="+WeChatProperties.APP_SECRET
                +"&code="+code
                +"&grant_type=authorization_code";
    }

    //获取小程序用户信息
    public static String getMiniUserUrl( String jsCode){
        return BASE_URL+"/sns/jscode2session?appid="+WeChatProperties.MINI_APP_ID+"&secret="+WeChatProperties.MINI_APP_SECRET+"&grant_type=authorization_code" +
                "&js_code="+jsCode;
    }



}
