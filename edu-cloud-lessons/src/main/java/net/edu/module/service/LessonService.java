package net.edu.module.service;


import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.LessonEntity;
import net.edu.module.query.LessonQuery;
import net.edu.module.vo.LessonVO;

import java.util.List;

/**
 * 课程表
 *
 * @author 马佳浩
 * @since 1.0.0 2022-09-15
 */
public interface LessonService extends BaseService<LessonEntity> {

    List<LessonVO> list(LessonQuery query);

//    void save(LessonVO vo);

    void update(LessonVO vo);

    void delete(List<Long> idList);

    void createLessons(List<LessonVO> voList);
}
