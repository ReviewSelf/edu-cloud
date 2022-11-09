package net.edu.module.service;

import cn.hutool.json.JSONObject;
import net.edu.module.entity.MsgLogEntity;
import net.edu.module.vo.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author weng
 * @date 2022/9/26 - 15:35
 **/
@Service
public interface WeChatMsgService {

    /**
     * 自动发送
     */
    void sentTemplate();

    /**
     * 网页批量发送
     * @param msgLogEntity
     */
    void sentMessage(MsgLogEntity msgLogEntity);

    void insertMsgLogClassOpenTemplate(List<WxClassOpenVO> list);

    void insertMsgLogWorkPublishTemplate(List<WxWorkPublishVO> list);

    void insertMsgLogLessonOpenTemplate(List<WxLessonOpenVO> list);

    void insertMsgLogSignSuccessTemplate(List<WxSignSuccessVO> list);

    void insertMsgLogWorkDeadlineTemplate(List<WxWorkDeadlineVO> list);

    void insertMsgLogLessonEvaluationTemplate(List<WxLessonEvaluationVO> list);

    void insertMsgLogExamArrangementTemplate(List<WxExamArrangementVO> list);

    /**
     * 消息群发
     * @param obj
     * @return
     */
    String sentBatchMessage(JSONObject obj);
}
