package net.edu.module.dao;

import net.edu.module.entity.MsgLogEntity;
import net.edu.module.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @author weng
 * @date 2022/10/9 - 11:13
 **/
@Mapper
public interface TemplateDao {

    List<MsgLogEntity> selectTemplate();

    void updateTemplate(Long id);

    int insertMsgLogClassOpenTemplate(String content, String sendTime, Long userId);

    int insertMsgLogWorkPublishTemplate(String content, String sendTime, Long userId);

    int insertMsgLogLessonOpenTemplate(String content, String sendTime, Long userId);

    int insertMsgLogSignSuccessTemplate(String content, String sendTime, Long userId);

    int insertMsgLogWorkDeadlineTemplate(String content, String sendTime, Long userId);
}
