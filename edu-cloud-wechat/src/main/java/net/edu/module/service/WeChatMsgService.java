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

    void insertMsgLogClassOpenTemplate(List<ClassOpenVO> list);

    void insertMsgLogWorkPublishTemplate(List<WorkPublishVO> list);

    void insertMsgLogLessonOpenTemplate(List<LessonOpenVO> list);

    void insertMsgLogSignSuccessTemplate(List<SignSuccessVO> list);

    void insertMsgLogWorkDeadlineTemplate(List<WorkDeadlineVO> list);

    void insertMsgLogLessonEvaluationTemplate(List<LessonEvaluationVO> list);

    /**
     * 消息群发
     * @param obj
     * @return
     */
    String sentBatchMessage(JSONObject obj);
}
