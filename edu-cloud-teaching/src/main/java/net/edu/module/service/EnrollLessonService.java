package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.EnrollJoinLessonEntity;
import net.edu.module.entity.EnrollLessonEntity;
import net.edu.module.entity.EnrollSelectOne;
import net.edu.module.entity.EnrollUserEntity;
import net.edu.module.query.EnrollLessonQuery;
import net.edu.module.vo.EnrollLessonVO;

import java.util.List;

public interface EnrollLessonService extends BaseService<EnrollLessonEntity> {

    PageResult<EnrollLessonVO> page(EnrollLessonQuery query);

    EnrollLessonEntity getLessonById(Long id);

    void updateLesson(EnrollLessonVO vo);

    void delete(List<Long> idList);

    List<EnrollSelectOne> getSelectOne();

    void saveLesson(EnrollLessonVO vo);

    void joinLesson(EnrollJoinLessonEntity entity);

    void joinLessonSys(EnrollUserEntity user);
}
