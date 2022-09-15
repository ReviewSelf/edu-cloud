package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.TeachLessonVO;
import net.edu.module.query.TeachLessonQuery;
import net.edu.module.entity.TeachLessonEntity;

import java.util.List;

/**
 * 课程表
 *
 * @author 翁瑞辰 babamu@126.com
 * @since 1.0.0 2022-09-09
 */
public interface TeachLessonService extends BaseService<TeachLessonEntity> {

    PageResult<TeachLessonVO> page(TeachLessonQuery query);

    void save(TeachLessonVO vo);

    void update(TeachLessonVO vo);

    void delete(List<Long> idList);
}