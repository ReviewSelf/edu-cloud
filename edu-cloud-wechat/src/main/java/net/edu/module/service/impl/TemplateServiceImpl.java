package net.edu.module.service.impl;

import com.alibaba.fastjson.JSONObject;
import net.edu.module.dao.MessageDao;
import net.edu.module.dao.TemplateDao;
import net.edu.module.entity.MsgLogEntity;
import net.edu.module.entity.WxFinalValue;
import net.edu.module.service.TemplateService;
import net.edu.module.untils.SubscriptionMessageUtil;
import net.edu.module.vo.ClassOpenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author weng
 * @date 2022/9/26 - 15:35
 **/
@Service
public class TemplateServiceImpl implements TemplateService {
    @Autowired
    MessageDao messageDao;
    @Autowired
    TemplateDao templateDao;


    @Override
    public int insertMsgLogClassOpenTemplate(ClassOpenVO vo) {
        JSONObject json=(JSONObject) JSONObject.toJSON(vo);
        String string = json.toJSONString();
        String sendTime = json.getString("sendTime");
        json.getLong("userId");
        System.out.println();

        Long userId = vo.getUserId();

        String classTime = vo.getClassTime();
        String location = vo.getLocation();
        ClassOpenVO classOpenVO = new ClassOpenVO();


        return 0;
//                templateDao.insertMsgLogClassOpenTemplate(content,sendTime,userId);
    }

    @Override
    public void sentTemplate() {
        MsgLogEntity msgLogEntities = templateDao.selectTemplate();
        Long userId = msgLogEntities.getUserId();
        String userOpenid = messageDao.selectUserOpenIdById(userId);
        String appid = WxFinalValue.APPID;
        String appSecret = WxFinalValue.APPSECRET;
        String content = msgLogEntities.getContent();
        String tempId = msgLogEntities.getTemplateId();
        int type = msgLogEntities.getType();
        //发送开课提醒
        if(type == 1)
            SubscriptionMessageUtil.sendClassOpenMsg(appid,appSecret,userOpenid,tempId,content);
        if(type == 2)
            SubscriptionMessageUtil.sendHomeWorkMsg(appid,appSecret,userOpenid,tempId,content);
        if(type == 5)
            SubscriptionMessageUtil.sendHomeworkSubmitMsg(appid,appSecret,userOpenid,tempId,content);
        //发送之后将标记更改为1
        Long id = msgLogEntities.getId();
        templateDao.updateTemplate(id);
    }
}