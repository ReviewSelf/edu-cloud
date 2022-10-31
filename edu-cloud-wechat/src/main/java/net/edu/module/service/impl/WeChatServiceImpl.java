package net.edu.module.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import net.edu.framework.common.exception.ServerException;
import net.edu.module.api.EduSysApi;
import net.edu.module.api.EduTeachApi;
import net.edu.module.entity.InMessage;
import net.edu.module.entity.OutMessage;
import net.edu.module.service.WeChatService;
import net.edu.module.untils.MenuUtils;
import net.edu.module.untils.WeChatApiUtils;
import net.edu.module.untils.WeChatProperties;
import net.edu.module.vo.SysWeChatLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/13 18:45
 * @Version: 1.0
 * @Description:
 */
@Service
@Slf4j
public class WeChatServiceImpl implements WeChatService {

    @Autowired
    private EduTeachApi eduTeachApi;
    @Autowired
    private EduSysApi eduSysApi;



    @Override
    public void getAccessToken(){
        log.info("执行到service");
        String url = WeChatApiUtils.TOKEN_URL;
        // 利用hutool的http工具类请求获取access_token
        System.out.println(url);
        String result = HttpUtil.get(url);
        System.out.println(result);
        // 将结果解析为json
        JSONObject jsonObject = JSONUtil.parseObj(result);
        // 获取access_token
        System.out.println(jsonObject.getStr("access_token"));
        WeChatProperties.TOKEN=jsonObject.getStr("access_token");
    }

    /**
     * 创建菜单
     * @return
     */
    @Override
    public String createMenu(){
        return HttpUtil.post(WeChatApiUtils.getMenuUrl(), MenuUtils.setMenuBody());
    }

    @Override
    public String getUnionId(String openId) {
        String url = WeChatApiUtils.getUnionUrl(openId);
        System.out.println("URL"+url);
        String result = HttpUtil.get(url);
        System.out.println("result:"+result);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        String unionId = jsonObject.getStr("unionid");
        System.out.println("Service"+unionId);
        return unionId;
    }



    @Override
    public JSONObject getOpenId(String code) {
        String url = WeChatApiUtils.getAccessTokenBaseUrl(code);
        String result = HttpUtil.get(url);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        String accessToken = jsonObject.getStr("access_token");
        String openId = jsonObject.getStr("openid");
        System.out.println(openId);
        return jsonObject;
    }

    @Override
    public Object handleMessage(InMessage inMessage) {
        String enrollmentUrl = WeChatApiUtils.getAuthBaseUrl(WeChatProperties.CLASS_URL,WeChatApiUtils.SNSAPI_USERINFO);
        log.info(inMessage.toString());
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
                log.info(openId);
                String unionId = getUnionId(openId);
                eduTeachApi.insertOpenId(openId,unionId);
                outMessage.setMsgType("text");
                outMessage.setContent("欢迎关注编程少年公众号~~~点击下方报名课程可以了解我们的课程并进行报名");


            }
            else if("CLICK".equals(event)){
                String eventKey = inMessage.getEventKey();
            }

        }

        return outMessage;
    }

    @Override
    public Object miniLogin(String code) {
        System.out.println(WeChatApiUtils.getMiniUserUrl(code));
        String result =  HttpUtil.get(WeChatApiUtils.getMiniUserUrl(code));
        System.out.println(result);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        String unionId=jsonObject.getStr("unionid");
        if(unionId!=null){
            SysWeChatLoginVO sysWeChatLoginVO=new SysWeChatLoginVO();
            sysWeChatLoginVO.setUnionId(unionId);
            return eduSysApi.wxMini(sysWeChatLoginVO);
        }else {
            throw new ServerException("unionId获取失败");
        }
    }

}
