package net.edu.module.service.impl;

import cn.hutool.json.JSONObject;
import net.edu.module.dao.MessageDao;
import net.edu.module.dao.TemplateDao;
import net.edu.module.dto.MessageDTO;
import net.edu.module.entity.MsgLogEntity;
import net.edu.module.service.WeChatMsgService;
import net.edu.module.untils.SubscriptionMessageUtil;
import net.edu.module.untils.WeChatProperties;
import net.edu.module.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


//    @Override
//    public void sentTemplate() {
//        List<MsgLogEntity> list = templateDao.selectTemplate();
//        for (MsgLogEntity msgLogEntities : list) {
//            Long userId = msgLogEntities.getUserId();
//            String userOpenid = messageDao.selectUserOpenIdById(userId);
//            String content = msgLogEntities.getContent();
//            String tempId = msgLogEntities.getTemplateId();
//            String userName = templateDao.selectUserNameById(userId);
//            int type = msgLogEntities.getType();
//            //根据数据库类型发送提醒
//            SubscriptionMessageUtil.chooseMsgTemplate(type,userOpenid, tempId, content,userName);
//            //发送之后将标记更改为1
//            Long id = msgLogEntities.getId();
//            templateDao.updateTemplate(id);
//        }
//    }
//
//    @Override
//    public void sentMessage(MsgLogEntity msgLogEntity) {
//        Long userId = msgLogEntity.getUserId();
//        //根据需要获取用户id和用户姓名，有些模板不需要用户姓名
//        String userOpenid = messageDao.selectUserOpenIdById(userId);
//        String userName = templateDao.selectUserNameById(userId);
//        String content = msgLogEntity.getContent();
//        String tempId = msgLogEntity.getTemplateId();
//        int type = msgLogEntity.getType();
//        //根据数据库类型发送提醒
//        SubscriptionMessageUtil.chooseMsgTemplate(type,userOpenid, tempId, content,userName);
//        //发送之后将标记更改为1
//        Long id = msgLogEntity.getId();
//        templateDao.updateTemplate(id);
//
//    }

//    @Override
//    public void insertMsgLogClassOpenTemplate(List<WxClassOpenVO> list) {
//        for (WxClassOpenVO vo : list) {
//            String content = vo.toJsonString();
//            String sendTime = vo.getSendTime();
//            Long userId = vo.getUserId();
//            templateDao.insertMsgLogClassOpenTemplate(content, sendTime, userId);
//        }
//    }
//
//
//    @Override
//    public void insertMsgLogWorkPublishTemplate(List<WxWorkPublishVO> list) {
//        for (WxWorkPublishVO vo : list) {
//            String content = vo.toJsonString();
//            String sendTime = vo.getSendTime();
//            Long userId = vo.getUserId();
//            templateDao.insertMsgLogWorkPublishTemplate(content, sendTime, userId);
//        }
//    }
//
//    @Override
//    public void insertMsgLogLessonOpenTemplate(List<WxLessonOpenVO> list) {
//        for (WxLessonOpenVO vo : list) {
//            String content = vo.toJsonString();
//            String sendTime = vo.getSendTime();
//            Long userId = vo.getUserId();
//            templateDao.insertMsgLogLessonOpenTemplate(content, sendTime, userId);
//        }
//    }
//
//    @Override
//    public void insertMsgLogSignSuccessTemplate(List<WxSignSuccessVO> list) {
//        for (WxSignSuccessVO vo : list) {
//            String content = vo.toJsonString();
//            String sendTime = vo.getSendTime();
//            Long userId = vo.getUserId();
//            templateDao.insertMsgLogSignSuccessTemplate(content, sendTime, userId);
//        }
//    }
//
//    @Override
//    public void insertMsgLogWorkDeadlineTemplate(List<WxWorkDeadlineVO> list) {
//        for (WxWorkDeadlineVO vo : list) {
//            String content = vo.toJsonString();
//            String sendTime = vo.getSendTime();
//            Long userId = vo.getUserId();
//            templateDao.insertMsgLogWorkDeadlineTemplate(content, sendTime, userId);
//        }
//    }
//
//    @Override
//    public void insertMsgLogLessonEvaluationTemplate(List<WxLessonEvaluationVO> list) {
//        System.out.println(list);
//        for (WxLessonEvaluationVO vo : list) {
//            String content = vo.toJsonString();
//            String sendTime = vo.getSendTime();
//            Long userId = vo.getUserId();
//            templateDao.insertMsgLogLessonEvaluationTemplate(content, sendTime, userId);
//        }
//    }
//
//    @Override
//    public void insertMsgLogExamArrangementTemplate(List<WxExamArrangementVO> list) {
//        for (WxExamArrangementVO vo : list) {
//            String content = vo.toJsonString();
//            String sendTime = vo.getSendTime();
//            Long userId = vo.getUserId();
//            templateDao.insertMsgLogExamArrangementTemplate(content, sendTime, userId);
//        }
//    }

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


    private List<String> getUserOpenId(MessageDTO messageDTO){
        List<String> userOpenId = new ArrayList<>();
        for (Long aLong : messageDTO.getUserId()) {
            String openId = messageDao.selectUserOpenIdById(aLong);
            userOpenId.add(openId);
        }
        return userOpenId;
    }
    /**
     * 发送作业布置消息
     * @param messageDTO
     */
    @Override
    public void sentHomeworkMsg(MessageDTO messageDTO) {
        List<String> userOpenId = getUserOpenId(messageDTO);
        String content = messageDTO.getContent();

        //创建被观察被对象--是否布置作业
        ObservableImpl homework = new ObservableImpl();
        //为被观察对象添加观察者
        for (String s : userOpenId) {
            //创建观察者对象
            ObserverImpl.HomeWorkMsg homeWorkMsg = new ObserverImpl.HomeWorkMsg();
            homeWorkMsg.userOpenid = s;
            homework.addObserver(homeWorkMsg);
        }
        //被观察者触发事件，通知所有观察者
        homework.notice(content);
    }


    /**
     * 发送班级开课
     * @param messageDTO
     */
    @Override
    public void sentClassOpenMsg(MessageDTO messageDTO) {
        List<String> userOpenId = getUserOpenId(messageDTO);
        String content = messageDTO.getContent();

        ObservableImpl classOpen = new ObservableImpl();
        for (String s : userOpenId) {
            ObserverImpl.ClassOpenMsg classOpenMsg = new ObserverImpl.ClassOpenMsg();
            classOpenMsg.userOpenid = s;
            classOpen.addObserver(classOpenMsg);
        }
        classOpen.notice(content);
    }

    /**
     * 发送课前通知
     * @param messageDTO
     */
    @Override
    public void sentLessonOpenMsg(MessageDTO messageDTO) {
        List<String> userOpenId = getUserOpenId(messageDTO);
        String content = messageDTO.getContent();

        //被观察者
        ObservableImpl lessonOpen = new ObservableImpl();
        for (String s : userOpenId) {
            ObserverImpl.LessonOpenMsg lessonOpenMsg = new ObserverImpl.LessonOpenMsg();
            lessonOpenMsg.userOpenid = s;
            lessonOpen.addObserver(lessonOpenMsg);
        }
        lessonOpen.notice(content);
    }

    /**
     * 签到成功通知
     * @param messageDTO
     */
    @Override
    public void sentSignSuccessMsg(MessageDTO messageDTO) {
        List<String> userOpenId = getUserOpenId(messageDTO);
        String content = messageDTO.getContent();

        //被观察者
        ObservableImpl signSuccess = new ObservableImpl();
        for (String s : userOpenId) {
            ObserverImpl.SignSuccessMsg signSuccessMsg = new ObserverImpl.SignSuccessMsg();
            signSuccessMsg.userOpenid = s;
            signSuccess.addObserver(signSuccessMsg);
        }
        signSuccess.notice(content);
    }

    /**
     * 作业截止
     * @param messageDTO
     */
    @Override
    public void sentWorkDeadlineMsg(MessageDTO messageDTO) {
        List<String> userOpenId = getUserOpenId(messageDTO);
        String content = messageDTO.getContent();

        //被观察者
        ObservableImpl workDeadline = new ObservableImpl();
        for (String s : userOpenId) {
            ObserverImpl.WorkDeadlineMsg workDeadlineMsg = new ObserverImpl.WorkDeadlineMsg();
            workDeadlineMsg.userOpenid = s;
            workDeadline.addObserver(workDeadlineMsg);
        }
        workDeadline.notice(content);
    }

    /**
     * 课堂评价
     * @param messageDTO
     */
    @Override
    public void sentLessonEvaluationMsg(MessageDTO messageDTO) {
        List<String> userOpenId = getUserOpenId(messageDTO);
        String content = messageDTO.getContent();

        //被观察者
        ObservableImpl lessonEvaluation = new ObservableImpl();
        for (String s : userOpenId) {
            ObserverImpl.LessonEvaluationMsg lessonEvaluationMsg = new ObserverImpl.LessonEvaluationMsg();
            lessonEvaluationMsg.userOpenid = s;
            lessonEvaluation.addObserver(lessonEvaluationMsg);
        }
        lessonEvaluation.notice(content);
    }

    /**
     * 考试安排
     * @param messageDTO
     */
    @Override
    public void sentExamArrangementMsg(MessageDTO messageDTO) {
        List<String> userOpenId = getUserOpenId(messageDTO);
        String content = messageDTO.getContent();

        //被观察者
        ObservableImpl examArrangement = new ObservableImpl();
        for (String s : userOpenId) {
            ObserverImpl.ExamArrangementMsg examArrangementMsg = new ObserverImpl.ExamArrangementMsg();
            examArrangementMsg.userOpenid = s;
            examArrangement.addObserver(examArrangementMsg);
        }
        examArrangement.notice(content);
    }
}
