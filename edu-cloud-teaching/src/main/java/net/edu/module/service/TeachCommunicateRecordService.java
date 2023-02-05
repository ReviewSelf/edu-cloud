package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.TeachCommunicateRecordEntity;
import net.edu.module.query.TeachCommunicateRecordQuery;
import net.edu.module.vo.TeachCommunicateRecordVO;

import java.util.List;

/**
 * 沟通记录表
 *
 * @author sqw 
 * @since 1.0.0 2023-02-05
 */
public interface TeachCommunicateRecordService extends BaseService<TeachCommunicateRecordEntity> {

    PageResult<TeachCommunicateRecordVO> page(TeachCommunicateRecordQuery query);

    void save(TeachCommunicateRecordVO vo);

    void update(TeachCommunicateRecordVO vo);

    void delete(List<Long> idList);
}