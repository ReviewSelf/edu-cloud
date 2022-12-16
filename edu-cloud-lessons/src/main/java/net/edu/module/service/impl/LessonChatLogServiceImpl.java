package net.edu.module.service.impl;


import net.edu.framework.common.utils.DateUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.dao.LessonChatLogDao;
import net.edu.module.entity.LessonChatLogEntity;
import net.edu.module.query.LessonChatLogQuery;
import net.edu.module.service.LessonChatLogService;
import net.edu.module.vo.LessonChatLogVO;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @author 马佳浩
 * @date 2022/12/9 09:14:13
 * @description
 */
@Service
public class LessonChatLogServiceImpl extends BaseServiceImpl<LessonChatLogDao, LessonChatLogEntity> implements LessonChatLogService {

    @Override
    public List<LessonChatLogVO> page(LessonChatLogQuery query) {

        List<LessonChatLogVO> list = baseMapper.selectPage(query);
        return list;
    }

    @Override
    public void save(LessonChatLogVO vo) {
        LessonChatLogEntity entity=new LessonChatLogEntity();
        entity.setLessonId(vo.getLessonId());
        entity.setUserId(vo.getUserId());
        entity.setMsg(vo.getMsg());
        entity.setCreateTime(DateUtils.parse(vo.getCreateTime(),DateUtils.DATE_TIME_PATTERN));
        save(entity);
    }


}
