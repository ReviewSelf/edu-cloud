package net.edu.module.entity;

/**
 * @author weng
 * @date 2022/9/27 - 16:33
 **/
public class WxFinalValue {

    public static final String WX_URL = "https://api.weixin.qq.com/cgi-bin";

    public static final String APPID = "wx5d0fc93575b299a8";

    public static final String APPSECRET = "b74a02008ff43c3a7d1d75012a82d3a4";

//    public static final AccessToken ACCESS_TOKEN = ;

    public static final String TOKEN_URL = WX_URL + "/token?grant_type=client_credential&appid=" + APPID +  "&secret=" + APPSECRET;

    public static final String MENU_URL = WX_URL + "/menu/create?access_token="  ;

    public static final String UNION_URL = WX_URL + "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" ;

    public static final String AUTH_BASE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE#wechat_redirect";

    //2.通过code换取的是一个特殊的网页授权access_token
    public static final String ACCESS_TOKEN_BASE_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    //3.access_token拥有较短的有效期，当access_token超时后，可以使用refresh_token进行刷新，refresh_token有效期为30天，当refresh_token失效之后，需要用户重新授权。
    public static final String ACCESS_TOKEN_REFRESH_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";

    //4.通过access_token和openid拉取用户信息
    public static final String INFO_BASE_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    //允许的范围
    public static final String SCOPE = "snsapi_userinfo";

}