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
import net.edu.module.service.SysUserService;
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

    @Autowired
    private SysUserService sysUserService;

    @Override
    public void getAccessToken(){
        log.debug("执行到service");
        String url = WeChatApiUtils.TOKEN_URL;
        // 利用hutool的http工具类请求获取access_token
        String result = HttpUtil.get(url);
        // 将结果解析为json
        JSONObject jsonObject = JSONUtil.parseObj(result);
        // 获取access_token
        WeChatProperties.TOKEN=jsonObject.getStr("access_token");
    }

    /**
     * 创建菜单
     * @return
     */
    @Override
    public String createMenu(){
        return HttpUtil.post(WeChatApiUtils.MENU_URL, MenuUtils.setMenuBody());
    }

    @Override
    public String getUnionId(String openId) {
        String url = WeChatApiUtils.getUnionUrl(openId);
        String result = HttpUtil.get(url);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        return jsonObject.getStr("union_id");
    }



    @Override
    public String getOpenId(String code) {
        String url = WeChatApiUtils.getAccessTokenBaseUrl(code);
        String result = HttpUtil.get(url);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        String accessToken = jsonObject.getStr("access_token");
        String openId = jsonObject.getStr("openid");
        return openId;
    }

    @Override
    public Object handleMessage(InMessage inMessage) {
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
//                String unionId = weChatService.getUnionId(openId);
                eduTeachApi.insertOpenId(openId);
                outMessage.setMsgType("text");
                outMessage.setContent("欢迎关注编程少年公众号~~~点击下方报名课程可以了解我们的课程并进行报名");
            }
            else if("CLICK".equals(event)){
                String eventKey = inMessage.getEventKey();
                System.out.println(eventKey);
                if(eventKey.equals("12")){
                    System.out.println("点击了账号解绑");
                    String openId = inMessage.getFromUserName();
                    sysUserService.updateOpenIdByUsername(null,null,openId);
                }
            }

        }

        return outMessage;
    }

    @Override
    public Object miniLogin(String code) {
        String result =  HttpUtil.get(WeChatApiUtils.getMiniUserUrl(code));
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
