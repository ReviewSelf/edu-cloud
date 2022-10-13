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
public interface TemplateService {

    /**
     * 自动发送
     */
    void sentTemplate();

    /**
     * 网页批量发送
     * @param msgLogEntity
     */
    void sentMessage(MsgLogEntity msgLogEntity);

    int insertMsgLogClassOpenTemplate(List<ClassOpenVO> list);

    int insertMsgLogWorkPublishTemplate(List<WorkPublishVO> list);

    int insertMsgLogLessonOpenTemplate(List<LessonOpenVO> list);

    int insertMsgLogSignSuccessTemplate(List<SignSuccessVO> list);

    int insertMsgLogWorkDeadlineTemplate(List<WorkDeadlineVO> list);

    /**
     * 消息群发
     * @param obje
     * @return
     */
    String sentBatchMessage(JSONObject obje);
}
