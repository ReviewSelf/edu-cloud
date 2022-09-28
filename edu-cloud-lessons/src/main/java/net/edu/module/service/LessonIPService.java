package net.edu.module.service;


import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.LessonIPVO;
import net.edu.module.query.LessonIPQuery;
import net.edu.module.entity.LessonIPEntity;

import java.util.List;

/**
 * 1
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-15
 */
public interface LessonIPService extends BaseService<LessonIPEntity> {

    List<LessonIPVO> list(LessonIPQuery query);

    void save(LessonIPVO vo);

    void update(LessonIPVO vo);

    void delete(List<Long> idList);

    Boolean ipJudge(Long lessonId,String ip);
}