package net.edu.module.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import net.edu.module.entity.*;
import net.edu.module.service.WxService;
import net.edu.module.vo.WxToken;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author weng
 * @date 2022/9/24 - 13:57
 **/
@Slf4j
@Service
public class WxServiceImpl implements WxService {

    @Override
    public void getAccessToken(){
        log.debug("执行到service");
        String url = WxFinalValue.TOKEN_URL;
        // 利用hutool的http工具类请求获取access_token
        String result = HttpUtil.get(url);
        System.out.println(result);
        // 将结果解析为json
        JSONObject jsonObject = JSONUtil.parseObj(result);
        // 获取access_token
        String accessToken = jsonObject.getStr("access_token");
        WxToken.token=accessToken;
    }
    /*
    创建菜单
     */
    @Override
    public String createMenu(){
        String accessToken = WxToken.token;
        String url = WxFinalValue.MENU_URL + accessToken;
        String redirectUrl = "http://www.proshaonian.com/#/class";
        // 创建菜单的请求体
        String CodeUrl;
        try {
            CodeUrl = WxFinalValue.AUTH_BASE_URL.replace("APPID",WxFinalValue.APPID)
                    .replace("REDIRECT_URI", URLEncoder.encode(redirectUrl, "UTF-8"))
                    .replace("SCOPE",WxFinalValue.SCOPE);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("codeURL"+CodeUrl);
//https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf824deebaddd5375&redirect_uri=http%3A%2F%2F124.71.130.128%2F%23%2Fclass&response_type=code&scope=snsapi_userinfo#wechat_redirect


        CommonButton mainBtn2 = new CommonButton();

        mainBtn2.setName("课程报名");

        mainBtn2.setType("view");

        mainBtn2.setUrl(CodeUrl);


        Menu menu = new Menu();

        menu.setButton(new Button[] {mainBtn2});

        JSONObject object = new JSONObject(menu);

        String body = object.toString();

        return HttpUtil.post(url, body);
    }

    @Override
    public String getUnionId(String openId) {
        String accessToken = WxToken.token;
        String url = WxFinalValue.UNION_URL+accessToken+"&openid="+openId;
//        System.out.println(url);
        String result = HttpUtil.get(url);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        String unionId = jsonObject.getStr("union_id");
        return unionId;
    }

    @Override
    public void template(){
        String accessToken = WxToken.token;
        String setUrl = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=" + accessToken;
        String body="{\n" +
                "    \"industry_id1\":\"16\",\n" +
                "    \"industry_id2\":\"17\"\n" +
                "}";
        String result = HttpUtil.post(setUrl, body);
        String getUrl = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=" + accessToken;
        String info = HttpUtil.get(getUrl);
        String getTemplateIdUrl = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=" + accessToken;
        System.out.println(info);
    }

    @Override
    public String getOpenId(String code) {
        String url = WxFinalValue.ACCESS_TOKEN_BASE_URL.replace("APPID",WxFinalValue.APPID)
                .replace("SECRET",WxFinalValue.APPSECRET)
                .replace("CODE",code);
        String result = HttpUtil.get(url);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        System.out.println(jsonObject);
        String accessToken = jsonObject.getStr("access_token");
        String openId = jsonObject.getStr("openid");
        return openId;
    }

}
