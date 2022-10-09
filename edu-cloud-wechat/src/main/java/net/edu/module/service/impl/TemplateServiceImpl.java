package net.edu.module.service.impl;



import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
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

        String content = vo.toJsonString();
        String sendTime = vo.getSendTime();
        Long userId = vo.getUserId();

        templateDao.insertMsgLogClassOpenTemplate(content,sendTime,userId);
        return 0;

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