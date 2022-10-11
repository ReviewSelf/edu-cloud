package net.edu.module.service.impl;

import net.edu.module.dao.MessageDao;
import net.edu.module.dao.TemplateDao;
import net.edu.module.entity.MsgLogEntity;
import net.edu.module.entity.WxFinalValue;
import net.edu.module.service.TemplateService;
import net.edu.module.untils.SubscriptionMessageUtil;
import net.edu.module.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
    public void sentTemplate() {
        List<MsgLogEntity> list = templateDao.selectTemplate();
        for (MsgLogEntity msgLogEntities: list){
            Long userId = msgLogEntities.getUserId();
            String userOpenid = messageDao.selectUserOpenIdById(userId);
            String appid = WxFinalValue.APPID;
            String appSecret = WxFinalValue.APPSECRET;
            String content = msgLogEntities.getContent();
            String tempId = msgLogEntities.getTemplateId();
            String userName = templateDao.selectUserNameById(userId);
            int type = msgLogEntities.getType();
            //根据数据库类型发送提醒
            if(type == 1)
                SubscriptionMessageUtil.sendClassOpenMsg(appid,appSecret,userOpenid,tempId,content);
            if(type == 2)
                SubscriptionMessageUtil.sendHomeWorkMsg(appid,appSecret,userOpenid,tempId,content,userName);
            if(type == 3)
                SubscriptionMessageUtil.sendLessonOpenMsg(appid,appSecret,userOpenid,tempId,content);
            if(type == 4)
                SubscriptionMessageUtil.sendSignSuccessMsg(appid,appSecret,userOpenid,tempId,content,userName);
            if(type == 5)
                SubscriptionMessageUtil.sendHomeworkSubmitMsg(appid,appSecret,userOpenid,tempId,content);
            //发送之后将标记更改为1
            Long id = msgLogEntities.getId();
            templateDao.updateTemplate(id);
        }
    }

    @Override
    public void sentMessage(MsgLogEntity msgLogEntity) {

            Long userId = msgLogEntity.getUserId();
            String userOpenid = messageDao.selectUserOpenIdById(userId);
            String userName = templateDao.selectUserNameById(userId);
            String appid = WxFinalValue.APPID;
            String appSecret = WxFinalValue.APPSECRET;
            String content = msgLogEntity.getContent();
            String tempId = msgLogEntity.getTemplateId();

            int type = msgLogEntity.getType();
            //根据数据库类型发送提醒
            if(type == 1)
                SubscriptionMessageUtil.sendClassOpenMsg(appid,appSecret,userOpenid,tempId,content);
            if(type == 2)
                SubscriptionMessageUtil.sendHomeWorkMsg(appid,appSecret,userOpenid,tempId,content,userName);
            if(type == 3)
                SubscriptionMessageUtil.sendLessonOpenMsg(appid,appSecret,userOpenid,tempId,content);
            if(type == 4)
                SubscriptionMessageUtil.sendSignSuccessMsg(appid,appSecret,userOpenid,tempId,content,userName);
            if(type == 5)
                SubscriptionMessageUtil.sendHomeworkSubmitMsg(appid,appSecret,userOpenid,tempId,content);
            //发送之后将标记更改为1
            Long id = msgLogEntity.getId();
            templateDao.updateTemplate(id);

    }
    @Override
    public int insertMsgLogClassOpenTemplate(List<ClassOpenVO> list) {

        for (ClassOpenVO vo: list) {
            String content = vo.toJsonString();
            String sendTime = vo.getSendTime();
            Long userId = vo.getUserId();
            templateDao.insertMsgLogClassOpenTemplate(content,sendTime,userId);
        }

        return 1;

    }


    @Override
    public int insertMsgLogWorkPublishTemplate(List<WorkPublishVO> list) {
        for (WorkPublishVO vo: list) {
            String content = vo.toJsonString();
            String sendTime = vo.getSendTime();
            Long userId = vo.getUserId();
            templateDao.insertMsgLogWorkPublishTemplate(content,sendTime,userId);
        }

        return 1;
    }

    @Override
    public int insertMsgLogLessonOpenTemplate(List<LessonOpenVO> list) {
        for (LessonOpenVO vo: list) {
            String content = vo.toJsonString();
            String sendTime = vo.getSendTime();
            Long userId = vo.getUserId();
            templateDao.insertMsgLogLessonOpenTemplate(content,sendTime,userId);
        }
        return 1;
    }

    @Override
    public int insertMsgLogSignSuccessTemplate(List<SignSuccessVO> list) {
        for (SignSuccessVO vo: list) {
            String content = vo.toJsonString();
            String sendTime = vo.getSendTime();
            Long userId = vo.getUserId();
            templateDao.insertMsgLogSignSuccessTemplate(content,sendTime,userId);
        }
        return 1;
    }

    @Override
    public int insertMsgLogWorkDeadlineTemplate(List<WorkDeadlineVO> list) {
        for (WorkDeadlineVO vo: list) {
            String content = vo.toJsonString();
            String sendTime = vo.getSendTime();
            Long userId = vo.getUserId();
            templateDao.insertMsgLogWorkDeadlineTemplate(content,sendTime,userId);
        }

        return 1;
    }
}