package net.edu.module.controller;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSONArray;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.entity.*;
import com.alibaba.fastjson.JSON;
import net.edu.module.service.WxService;
import net.edu.module.untils.SubscriptionMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private WxService wxService;

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
        String openid = "oPybX5iiL-yKwxOPlYl3yKcpbAEM";		// 发送给指定的用户
        String serviceName = "E";
        String orderNo = "2";

        SubscriptionMessageUtil.sendOrderMsg("wx33ea578d3bf919f4", "934da208d55b9661b1df30065904bf82", openid, orderNo, serviceName);
    }


    /**
     * 接受用户的信息或菜单的响应
     * @param inMessage
     * @return
     */
    @PostMapping(value="/wxToken",produces = "application/xml;charset=UTF-8")
    public Object  handleMessage(@RequestBody InMessage inMessage) {
        System.out.println(inMessage);
        // 创建响应消息实体对象
        OutMessage outMessage = new OutMessage();
        // 把原来的接收方设置为发送方
        outMessage.setFromUserName(inMessage.getToUserName());
        // 把原来的发送方设置为接收方
        outMessage.setToUserName(inMessage.getFromUserName());
        // 设置消息类型
        outMessage.setMsgType(inMessage.getMsgType());
        // 设置消息时间
        outMessage.setCreateTime(System.currentTimeMillis() / 1000);
        // 根据接收到消息类型，响应对应的消息内容
        if ("text".equals(inMessage.getMsgType())){
            // 文本关键字回复
            String inContent = inMessage.getContent();
            if(inContent.contains("报名")){
                outMessage.setMsgType("text");
                outMessage.setContent("你输入了报名");
            }
            if(inContent.contains("注册")){
                outMessage.setMsgType("text");
                outMessage.setContent("你输入了注册");
            }

        }else if ("image".equals(inMessage.getMsgType())){
            // 图片
            outMessage.setMediaId(new String[]{inMessage.getMediaId()});
        }else if("event".equals(inMessage.getMsgType())){
            //获取推送的事件类型
            String event = inMessage.getEvent();
            //如果是关注事件
            if("subscribe".equals(event)){
                outMessage.setMsgType("text");
                outMessage.setContent("欢迎关注我的公众号~~~输入“注册”可以注册成为我们的用户，输入“报名”可以了解我们的课程并进行报名");
            }
            else if("CLICK".equals(event)){
                String eventKey = inMessage.getEventKey();
                if(eventKey.equals("12")){
                    outMessage.setMsgType("text");
                    outMessage.setContent("你点击了成为老师");
                }
                if(eventKey.equals("21")){
                    outMessage.setMsgType("text");
                    outMessage.setContent("你点击了试听课");
                }
                if(eventKey.equals("22")){
                    outMessage.setMsgType("text");
                    outMessage.setContent("你点击了正式课");
                }
                if(eventKey.equals("31")){
                    outMessage.setMsgType("text");
                    outMessage.setContent("你点击了我的班级");
                }
                if(eventKey.equals("32")){
                    outMessage.setMsgType("text");
                    outMessage.setContent("你点击了我的作业");
                }
                if(eventKey.equals("33")){
                    outMessage.setMsgType("text");
                    outMessage.setContent("你点击了我的考试");
                }
            }

        }
        return outMessage;
    }

    /**
     * 获取access_token
     * @return
     */
    @GetMapping("getAccessToken")
    public AccessToken getAccessToken(){
        return wxService.getAccessToken();
    }

    /**
     * 创建菜单
     * @param
     * @return
     */
    @GetMapping("createMenu")
    public String createMenu(){
        return wxService.createMenu();
    }

    @GetMapping("template")
    public void template(){
        wxService.template();
    }

    @PostMapping("test")
    public Result<String> test(@RequestBody JSONObject jsonObject){
        System.out.println(jsonObject.get("list"));
        List UserVO= (List) jsonObject.get("list");
        System.out.println(UserVO);
        List<Map> listMap = new ArrayList<Map>();

        Map map1 = new HashMap();
        map1.put("小明","员工");
        map1.put("小军","主管");
        String jsonString1= JSON.toJSONString(map1);
        System.out.println(jsonString1);

        Map map2 = new HashMap();
        map2.put("小王", "员工");
        map2.put("小红", "主管");

        listMap.add(map1);
        listMap.add(map2);
        Map map=(Map) JSONArray.parse(jsonString1);
        System.out.println(map.get("小明"));
        String jsonString2= JSON.toJSONString(listMap);
        System.out.println(jsonString2);
        wxService.messageTemplate(jsonObject);
        return Result.ok();
    }
}
