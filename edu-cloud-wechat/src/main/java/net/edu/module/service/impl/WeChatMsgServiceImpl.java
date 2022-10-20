package net.edu.module.service.impl;

import cn.hutool.json.JSONObject;
import net.edu.module.dao.MessageDao;
import net.edu.module.dao.TemplateDao;
import net.edu.module.entity.MsgLogEntity;
import net.edu.module.service.WeChatMsgService;
import net.edu.module.untils.SubscriptionMessageUtil;
import net.edu.module.untils.WeChatProperties;
import net.edu.module.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author weng
 * @date 2022/9/26 - 15:35
 **/
@Service
public class WeChatMsgServiceImpl implements WeChatMsgService {
    @Autowired
    MessageDao messageDao;
    @Autowired
    TemplateDao templateDao;


    @Override
    public void sentTemplate() {
        List<MsgLogEntity> list = templateDao.selectTemplate();
        for (MsgLogEntity msgLogEntities : list) {
            Long userId = msgLogEntities.getUserId();
            String userOpenid = messageDao.selectUserOpenIdById(userId);
            String content = msgLogEntities.getContent();
            String tempId = msgLogEntities.getTemplateId();
            String userName = templateDao.selectUserNameById(userId);
            int type = msgLogEntities.getType();
            //根据数据库类型发送提醒
            SubscriptionMessageUtil.chooseMsgTemplate(type,userOpenid, tempId, content,userName);
            //发送之后将标记更改为1
            Long id = msgLogEntities.getId();
            templateDao.updateTemplate(id);
        }
    }

    @Override
    public void sentMessage(MsgLogEntity msgLogEntity) {
        Long userId = msgLogEntity.getUserId();
        //根据需要获取用户id和用户姓名，有些模板不需要用户姓名
        String userOpenid = messageDao.selectUserOpenIdById(userId);
        String userName = templateDao.selectUserNameById(userId);
        String content = msgLogEntity.getContent();
        String tempId = msgLogEntity.getTemplateId();
        int type = msgLogEntity.getType();
        //根据数据库类型发送提醒
        SubscriptionMessageUtil.chooseMsgTemplate(type,userOpenid, tempId, content,userName);
        //发送之后将标记更改为1
        Long id = msgLogEntity.getId();
        templateDao.updateTemplate(id);

    }

    @Override
    public void insertMsgLogClassOpenTemplate(List<ClassOpenVO> list) {
        for (ClassOpenVO vo : list) {
            String content = vo.toJsonString();
            String sendTime = vo.getSendTime();
            Long userId = vo.getUserId();
            templateDao.insertMsgLogClassOpenTemplate(content, sendTime, userId);
        }
    }


    @Override
    public void insertMsgLogWorkPublishTemplate(List<WorkPublishVO> list) {
        for (WorkPublishVO vo : list) {
            String content = vo.toJsonString();
            String sendTime = vo.getSendTime();
            Long userId = vo.getUserId();
            templateDao.insertMsgLogWorkPublishTemplate(content, sendTime, userId);
        }
    }

    @Override
    public void insertMsgLogLessonOpenTemplate(List<LessonOpenVO> list) {
        for (LessonOpenVO vo : list) {
            String content = vo.toJsonString();
            String sendTime = vo.getSendTime();
            Long userId = vo.getUserId();
            templateDao.insertMsgLogLessonOpenTemplate(content, sendTime, userId);
        }
    }

    @Override
    public void insertMsgLogSignSuccessTemplate(List<SignSuccessVO> list) {
        for (SignSuccessVO vo : list) {
            String content = vo.toJsonString();
            String sendTime = vo.getSendTime();
            Long userId = vo.getUserId();
            templateDao.insertMsgLogSignSuccessTemplate(content, sendTime, userId);
        }
    }

    @Override
    public void insertMsgLogWorkDeadlineTemplate(List<WorkDeadlineVO> list) {
        for (WorkDeadlineVO vo : list) {
            String content = vo.toJsonString();
            String sendTime = vo.getSendTime();
            Long userId = vo.getUserId();
            templateDao.insertMsgLogWorkDeadlineTemplate(content, sendTime, userId);
        }
    }

    @Override
    public void insertMsgLogLessonEvaluationTemplate(List<LessonEvaluationVO> list) {
        System.out.println(list);
        for (LessonEvaluationVO vo : list) {
            String content = vo.toJsonString();
            String sendTime = vo.getSendTime();
            Long userId = vo.getUserId();
            templateDao.insertMsgLogLessonEvaluationTemplate(content, sendTime, userId);
        }
    }
    /**
     * 未完成
     */
    @Override
    public String sentBatchMessage(JSONObject obj) {
        String content = obj.getStr("content");//群发内容
        String person = obj.getStr("person");
        String code = SubscriptionMessageUtil.GroupMessage(WeChatProperties.TOKEN, content, person);//群发消息
        return code;
    }
}
