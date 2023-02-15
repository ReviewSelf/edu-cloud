package net.edu.module.service;

import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.TeachClassRecordEntity;
import net.edu.framework.common.page.PageResult;
import net.edu.module.query.TeachClassRecordQuery;
import net.edu.module.vo.TeachClassRecordVO;

import java.util.List;

/**
 * 学生班级记录
 *
 * @author sqw 
 * @since 1.0.0 2023-02-08
 */
public interface TeachClassRecordService extends BaseService<TeachClassRecordEntity> {

    PageResult<TeachClassRecordVO> page(TeachClassRecordQuery query);

    void save(TeachClassRecordVO vo);

    void update(TeachClassRecordVO vo);

    void delete(List<Long> idList);
}