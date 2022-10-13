package net.edu.module.controller;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.edu.framework.common.utils.Result;
import net.edu.module.untils.WeChatProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/11 14:11
 * @Version: 1.0
 * @Description:
 * 微信扫码登录，条件不允许暂不开发
 */
@RestController
@AllArgsConstructor
public class WxLoginController {
    /**生成二维码
     *
     */
    @SneakyThrows
    @GetMapping("genQRCode")
    public Result<Map> genQRCode(){
        Map<String,Object> map=new HashMap<>();
        map.put("appid", WeChatProperties.APP_ID);
        map.put("scope","snsapi_login");
//        map.put("redirect_uri", URLEncoder.encode((WeChatProperties.REDIRECT_URL),"UTF-8"));
        map.put("state",System.currentTimeMillis());
        return Result.ok(map);
    }

}
