package net.edu.module.controller;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.entity.*;
import net.edu.module.service.MessageService;
import net.edu.module.service.WxService;
import net.edu.module.untils.SubscriptionMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
* 新增模块演示
*
* @author 阿沐 babamu@126.com
*/
@RestController
@RequestMapping("wx")
@Tag(name="消息推送")
@AllArgsConstructor
public class WxController {

    @Autowired
    private WxService wxService;
    @Autowired
    private MessageService messageService;




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

        SubscriptionMessageUtil.sendOrderMsg("wx33ea578wxf824deebaddd5375d3bf919f4", "934da208d55b9661b1df30065904bf82", openid, orderNo, serviceName);
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


        }else if ("image".equals(inMessage.getMsgType())){
            // 图片
            outMessage.setMediaId(new String[]{inMessage.getMediaId()});
        }else if("event".equals(inMessage.getMsgType())){
            //获取推送的事件类型
            String event = inMessage.getEvent();
            //如果是关注事件
            if("subscribe".equals(event)){
                String openId = inMessage.getFromUserName();
//                String unionId = wxService.getUnionId(openId);
                messageService.insertOpenId(openId);
                outMessage.setMsgType("text");
                outMessage.setContent("欢迎关注编程少年公众号~~~点击下方报名课程可以了解我们的课程并进行报名");
            }
            else if("CLICK".equals(event)){

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

    @PostMapping("post")
    @Operation(summary = "注册")
    public Result post(@RequestBody UserEntity userEntity){
        messageService.post(userEntity);
        System.out.println(userEntity);
        if(userEntity.getPurpose()=="" || userEntity.getPurpose()==null){
            Integer classId = userEntity.getClassId();
            String openId = userEntity.getOpenId();
            messageService.insertClassUser(classId,openId);
        }
        return Result.ok();
    }

    @GetMapping("union")
    public String getUnionId(@RequestParam("openId") String openId){
        return wxService.getUnionId(openId);
    }

    /**
     * 通过code获取openId
     * @param code
     * @return
     */
    @GetMapping("code")
    public Result<String> getOpenId(@RequestParam("code") String code){
        System.out.println(code);
        return Result.ok(wxService.getOpenId(code));
    }
}
