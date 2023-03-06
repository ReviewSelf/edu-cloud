package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.TeachClassHoursFlowRecordEntity;
import net.edu.module.query.TeachClassHoursFlowRecordQuery;
import net.edu.module.vo.TeachClassHoursFlowRecordVO;

import java.util.List;

/**
 * 课时流水管理
 *
 * @author sqw 
 * @since  2023-03-06
 */
public interface TeachClassHoursFlowRecordService extends BaseService<TeachClassHoursFlowRecordEntity> {

    PageResult<TeachClassHoursFlowRecordVO> page(TeachClassHoursFlowRecordQuery query);

    void save(TeachClassHoursFlowRecordVO vo);

    void update(TeachClassHoursFlowRecordVO vo);

    void delete(List<Long> idList);

    TeachClassHoursFlowRecordEntity getByLessonIdAndStudId(Long lessonId, Long stuId);
}