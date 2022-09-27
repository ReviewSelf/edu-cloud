package net.edu.module.controller;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.module.untils.SubscriptionMessageUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
* 新增模块演示
*
* @author 阿沐 babamu@126.com
*/
@RestController
@RequestMapping("news")
@Tag(name="消息推送")
@AllArgsConstructor
public class NewsController {

    @GetMapping("/wxToken")
    public String wxToken(String signature, String timestamp, String nonce, String echostr) {
        String token = "abc";

        List<String> strList = new ArrayList<>();
        strList.add(token);
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
            System.out.println("验证通过");
            //验签成功后响应指定参数
            return echostr;
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create() {
        // 发送给指定的用户
        String openid = "";
        String serviceName = "E";
        String orderNo = "2";

        SubscriptionMessageUtil.sendOrderMsg("wx33ea578d3bf919f4", "934da208d55b9661b1df30065904bf82", openid, orderNo, serviceName);
    }

    @PostMapping("/wxToken")
    public String getNews() {
        System.out.println("444444444");
        return "pk";
    }

}
