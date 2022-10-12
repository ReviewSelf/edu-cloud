package net.edu.module.controller;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.api.EduTeachApi;
import net.edu.module.entity.*;
import net.edu.module.service.TemplateService;
import net.edu.module.service.WxService;
import net.edu.module.vo.*;
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
    private EduTeachApi eduTeachApi;

    @Autowired
    private WxService wxService;
    @Autowired
    private TemplateService templateService;


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

    /**
     * 发送模板消息(定时器自动查询）
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public void sendTemplateMessage() {
        templateService.sentTemplate();
    }

    /**
     * 手动发送
     * @param msgLogEntity
     * @return
     */
    @RequestMapping(value = "/msg", method = RequestMethod.POST)
    public Result<String> sendMessage(@RequestBody MsgLogEntity msgLogEntity) {
        templateService.sentMessage(msgLogEntity);
        return Result.ok();
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
                Result<String> stringResult = eduTeachApi.insertOpenId(openId);
                System.out.println(stringResult);
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
    public void getAccessToken(){
        System.out.println("66666666");
        wxService.getAccessToken();
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


    @PostMapping("post")
    @Operation(summary = "注册")
    public Result post(@RequestBody EnrollUserVO enrollUserVO){
        eduTeachApi.post(enrollUserVO);
        if(enrollUserVO.getPurpose()=="" || enrollUserVO.getPurpose()==null){
            Integer classId = enrollUserVO.getClassId();
            String openId = enrollUserVO.getOpenId();
            eduTeachApi.insertClassUser(classId,openId);
//            messageService.insertClassUser(classId,openId);
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

    @PostMapping("classOpen")
    public Result<String> insertClassOpenTemplate(@RequestBody List<ClassOpenVO> vo){
        templateService.insertMsgLogClassOpenTemplate(vo);
        return Result.ok();
    }

    @PostMapping("workPublish")
    public Result<String> insertWorkPublishTemplate(@RequestBody List<WorkPublishVO> vo){
        templateService.insertMsgLogWorkPublishTemplate(vo);
        return Result.ok();
    }

    @PostMapping("lessonOpen")
    public Result<String> insertLessonOpenTemplate(@RequestBody List<LessonOpenVO> vo){
        templateService.insertMsgLogLessonOpenTemplate(vo);
        return Result.ok();
    }

    @PostMapping("signSuccess")
    public Result<String> insertSignSuccessTemplate(@RequestBody List<SignSuccessVO> vo){
        templateService.insertMsgLogSignSuccessTemplate(vo);
        return Result.ok();
    }

    @PostMapping("workDeadline")
    public Result<String> insertWorkDeadlineTemplate(@RequestBody List<WorkDeadlineVO> vo){
        templateService.insertMsgLogWorkDeadlineTemplate(vo);
        return Result.ok();
    }
}


/**
 * 接受数据格式样例，为ArrayList
 [
 {
 "lessonName":"贪心算法",
 "lessonTime":"2022-10-10 18:50:00",
 "lessonLocation":"翠柏校区",
 "sendTime":"2022-10-10 17:50:00",
 "userId":10020
 },
 {
 "lessonName":"贪心算法",
 "lessonTime":"2022-10-10 18:50:00",
 "lessonLocation":"翠柏校区",
 "sendTime":"2022-10-10 17:50:00",
 "userId":10043
 }
 ]
 */
















