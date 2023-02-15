package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.ClassHoursFlowRecordEntity;
import net.edu.module.query.ClassHoursFlowRecordQuery;
import net.edu.module.vo.ClassHoursFlowRecordVO;

import java.util.List;

/**
 * 课时流水表
 *
 * @author sqw 
 * @since 1.0.0 2023-02-15
 */
public interface ClassHoursFlowRecordService extends BaseService<ClassHoursFlowRecordEntity> {

    PageResult<ClassHoursFlowRecordVO> page(ClassHoursFlowRecordQuery query);

    void save(ClassHoursFlowRecordVO vo);

    void update(ClassHoursFlowRecordVO vo);

    void delete(List<Long> idList);
}