package net.edu.module.service;

import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.LessonChatLogEntity;
import net.edu.module.query.LessonChatLogQuery;
import net.edu.module.vo.LessonChatLogVO;

import java.util.List;


/**
 * @author 马佳浩
 * @date 2022/12/9 09:12:25
 * @description 聊天记录
 */
public interface LessonChatLogService extends BaseService<LessonChatLogEntity> {

    List<LessonChatLogVO> page(LessonChatLogQuery query);


    void save(LessonChatLogVO vo);
}
