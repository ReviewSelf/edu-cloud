package net.edu.module.controller;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.entity.*;
import net.edu.module.service.HomeWorkService;
import net.edu.module.service.MessageService;
import net.edu.module.service.WxService;
import net.edu.module.untils.SubscriptionMessageUtil;
import net.edu.module.vo.HomeWorkVO;
import net.edu.module.vo.WxChoiceProblemVO;
import net.edu.module.vo.WxFillProblemVO;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
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

    @Autowired
    private HomeWorkService homeWorkService;


    /**
     * 根据学生id获取对应课后作业
     *
     */
    @GetMapping("/homeWork")
    public List<HomeWorkVO> getStudentHomeWork(String studentId){

        List<HomeWorkVO> homeWorkVO=homeWorkService.getStudentHomeWork(studentId);
        return homeWorkVO;
    }

    /**
     * 获取选择题目信息
     */
    @GetMapping("/choiceProblemInfo")
    public List<WxChoiceProblemVO> getChoiceProblemInfo(String problemId){
        List<WxChoiceProblemVO> wxChoiceProblemVOS=homeWorkService.getChoiceProblemInfo(problemId);
        return wxChoiceProblemVOS;
    }

    /**
     * 获取填空题目信息
     */
    @GetMapping("/fillProblemInfo")
    public WxFillProblemVO GetFillProblemInfo(String problemId){
        WxFillProblemVO wxFillProblemVOS=homeWorkService.GetFillProblemInfo(problemId);
        return wxFillProblemVOS;
    }




//    /**
//     * 小程序通过code获取openID
//     */
//    @RequestMapping("/testopenid")
//    public String getUserInfo(@RequestParam(name = "code") String code) throws Exception {
//        System.out.println("code:" + code);
//        String url = "https://api.weixin.qq.com/sns/jscode2session";
//        url += "?wx5ad31bc765b5c885";//自己的appid
//        url += "&secret=e4842098b3bd2f76166b8aaacbcc8506";//自己的appSecret
//        url += "&js_code=" + code;
//        url += "&grant_type=authorization_code";
//        url += "&connect_redirect=1";
//        String res = null;
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        // DefaultHttpClient();
//        HttpGet httpget = new HttpGet(url);    //GET方式
//        CloseableHttpResponse response = null;
//        // 配置信息
//        RequestConfig requestConfig = RequestConfig.custom()          // 设置连接超时时间(单位毫秒)
//                .setConnectTimeout(5000)                    // 设置请求超时时间(单位毫秒)
//                .setConnectionRequestTimeout(5000)             // socket读写超时时间(单位毫秒)
//                .setSocketTimeout(5000)                    // 设置是否允许重定向(默认为true)
//                .setRedirectsEnabled(false).build();           // 将上面的配置信息 运用到这个Get请求里
//        httpget.setConfig(requestConfig);                         // 由客户端执行(发送)Get请求
//        response = httpClient.execute(httpget);                   // 从响应模型中获取响应实体
//        HttpEntity responseEntity = response.getEntity();
//        System.out.println("响应状态为:" + response.getStatusLine());
//        if (responseEntity != null) {
//            res = EntityUtils.toString(responseEntity);
//            System.out.println("响应内容长度为:" + responseEntity.getContentLength());
//            System.out.println("响应内容为:" + res);
//        }
//        // 释放资源
//        if (httpClient != null) {
//            httpClient.close();
//        }
//        if (response != null) {
//            response.close();
//        }
//        JSONObject jo = JSON.parseObject(res);
//        String openid = jo.getString("openid");
//        System.out.println("openid" + openid);
//        return openid;
//    }



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
