package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.ArchiveAssessVO;
import net.edu.module.query.ArchiveAssessQuery;
import net.edu.module.entity.ArchiveAssessEntity;
import net.edu.module.vo.ArchiveWeightTargetAssessVO;

import java.util.List;

/**
 * 考核点
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-26
 */
public interface ArchiveAssessService extends BaseService<ArchiveAssessEntity> {

    PageResult<ArchiveAssessVO> page(ArchiveAssessQuery query);

    ArchiveAssessVO selectArchiveAssessById(Long id);

    void save(ArchiveWeightTargetAssessVO vo);

    void update(ArchiveAssessVO vo);

    void delete(List<Long> idList);


}
