package net.edu.module.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import net.edu.module.entity.*;
import net.edu.module.service.WxService;
import org.springframework.stereotype.Service;

/**
 * @author weng
 * @date 2022/9/24 - 13:57
 **/
@Service
public class WxServiceImpl implements WxService {
    public static final String APPID = "wx5d0fc93575b299a8";
    public static final String APPSECRET = "b74a02008ff43c3a7d1d75012a82d3a4";

    AccessToken AccessToken = new AccessToken();
    @Override
    public AccessToken getAccessToken(){
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID +
                "&secret=" + APPSECRET;
        // 利用hutool的http工具类请求获取access_token
        String result = HttpUtil.get(url);
        // 将结果解析为json
        JSONObject jsonObject = JSONUtil.parseObj(result);
        // 获取access_token
        String accessToken = jsonObject.getStr("access_token");
        int expiresIn = jsonObject.getInt("expires_in");

        AccessToken.setToken(accessToken);
        AccessToken.setExpiresIn(expiresIn);
        return AccessToken;
    }
    /*
    创建菜单
     */
    @Override
    public String createMenu(){
        String accessToken = AccessToken.getToken();
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
        // 创建菜单的请求体
        CommonButton btn11 = new CommonButton();
        btn11.setName("成为学生");

        btn11.setType("view");

        btn11.setUrl("http://www.baidu.com");

        CommonButton btn12 = new CommonButton();

        btn12.setName("成为老师");

        btn12.setType("click");

        btn12.setKey("12");


        CommonButton btn21 = new CommonButton();

        btn21.setName("试听课");

        btn21.setType("click");

        btn21.setKey("21");

        CommonButton btn22 = new CommonButton();

        btn22.setName("正式课");

        btn22.setType("click");

        btn22.setKey("22");



        CommonButton btn31 = new CommonButton();

        btn31.setName("我的班级");

        btn31.setType("click");

        btn31.setKey("31");

        CommonButton btn32 = new CommonButton();

        btn32.setName("我的作业");

        btn32.setType("click");

        btn32.setKey("32");

        CommonButton btn33 = new CommonButton();

        btn33.setName("我的考试");

        btn33.setType("click");

        btn33.setKey("33");

        ComplexButton mainBtn1 = new ComplexButton();

        mainBtn1.setName("账号注册");

        mainBtn1.setSub_button(new CommonButton[] { btn11, btn12 });

        ComplexButton mainBtn2 = new ComplexButton();

        mainBtn2.setName("课程报名");

        mainBtn2.setSub_button(new CommonButton[] { btn21, btn22 });

        ComplexButton mainBtn3 = new ComplexButton();

        mainBtn3.setName("我的");

        mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33 });

        Menu menu = new Menu();

        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

        JSONObject object = new JSONObject(menu);

        String body = object.toString();

        return HttpUtil.post(url, body);
    }

//    @Override
//    public String getUnionId(String openId) {
//        String accessToken = AccessToken.getToken();
//        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+accessToken+"&openid="+openId;
//        System.out.println(url);
//        String result = HttpUtil.get(url);
//        JSONObject jsonObject = JSONUtil.parseObj(result);
//        String unionId = jsonObject.getStr("union_id");
//        return unionId;
//    }

    @Override
    public void template(){
        String accessToken = AccessToken.getToken();
        String setUrl = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=" + accessToken;
        String body="{\n" +
                "    \"industry_id1\":\"16\",\n" +
                "    \"industry_id2\":\"17\"\n" +
                "}";
        String result = HttpUtil.post(setUrl, body);
        String getUrl = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=" + accessToken;
        String info = HttpUtil.get(getUrl);
        System.out.println(info);
    }

}
