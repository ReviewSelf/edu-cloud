package net.edu.module.controller;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.entity.InMessage;
import net.edu.module.service.WeChatService;
import net.edu.module.untils.WeChatProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/13 19:06
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("wx")
@Tag(name="消息推送")
@AllArgsConstructor
public class WeChatController {

    @Autowired
    private WeChatService weChatService;



    /**
     * Token验证
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @GetMapping("/wxToken")
    public String wxToken(String signature, String timestamp, String nonce, String echostr) {

        List<String> strList = new ArrayList<>();
        strList.add(WeChatProperties.TOKEN);
        strList.add(timestamp);
        strList.add(nonce);
        //对参数进行字典序排序
        Collections.sort(strList);

        //生成待验签字符串
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(strList.get(0));
        stringBuilder.append(strList.get(1));
        stringBuilder.append(strList.get(2));
        String str = stringBuilder.toString();

        //按照指定算法生成签名（sha1）
        Digester sha1 = new Digester(DigestAlgorithm.SHA1);
        String digestHex = sha1.digestHex(str);

        //验签
        if (signature.equals(digestHex)) {
            //验签成功后响应指定参数
            return echostr;
        } else {
            return "error";
        }
    }

    /**
     * 获取access_token
     * @return
     */
    @GetMapping("getAccessToken")
    public void getAccessToken(){
        weChatService.getAccessToken();
    }

    /**
     * 创建菜单
     * @param
     * @return
     */
    @GetMapping("createMenu")
    public String createMenu(){
        return weChatService.createMenu();
    }



    @GetMapping("union")
    public String getUnionId(@RequestParam("openId") String openId){
        return weChatService.getUnionId(openId);
    }

    /**
     * 通过code获取openId
     * @param code
     * @return
     */
    @GetMapping("code")
    public Result<String> getOpenId(@RequestParam("code") String code){
        return Result.ok(weChatService.getOpenId(code));
    }

    /**
     * 接受用户的信息或菜单的响应
     * @param inMessage
     * @return
     */
    @PostMapping(value="/wxToken",produces = "application/xml;charset=UTF-8")
    public Object  handleMessage(@RequestBody InMessage inMessage) {
        return weChatService.handleMessage(inMessage);
    }


    /**
     * 小程序登录
     * @param code
     * @return
     */
    @GetMapping("login/mini")
    public Object miniLogin(@RequestParam("code") String code){
        return weChatService.miniLogin(code);
    }

}
