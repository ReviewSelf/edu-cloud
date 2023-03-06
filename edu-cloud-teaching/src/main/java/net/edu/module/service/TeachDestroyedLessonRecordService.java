package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.TeachDestroyedLessonRecordEntity;
import net.edu.module.query.TeachDestroyedLessonRecordQuery;
import net.edu.module.vo.TeachDestroyedLessonRecordListVO;
import net.edu.module.vo.TeachDestroyedLessonRecordVO;

import java.util.List;

/**
 * 消课管理
 *
 * @author sqw 
 * @since 1.0.0 2023-03-04
 */
public interface TeachDestroyedLessonRecordService extends BaseService<TeachDestroyedLessonRecordEntity> {

    PageResult<TeachDestroyedLessonRecordVO> page(TeachDestroyedLessonRecordQuery query);

    void save(TeachDestroyedLessonRecordVO vo);

    void update(TeachDestroyedLessonRecordVO vo);

    void delete(List<Long> idList);

    void saveList(TeachDestroyedLessonRecordListVO vo);
}