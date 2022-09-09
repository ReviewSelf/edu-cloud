package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.EnrollLessonEntity;
import net.edu.module.query.EnrollLessonQuery;
import net.edu.module.vo.EnrollLessonVO;

public interface EnrollLessonService extends BaseService<EnrollLessonEntity> {

    PageResult<EnrollLessonVO> page(EnrollLessonQuery query);

    void save(EnrollLessonVO vo);
}
