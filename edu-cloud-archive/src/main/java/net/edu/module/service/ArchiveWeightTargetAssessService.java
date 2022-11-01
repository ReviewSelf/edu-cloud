package net.edu.module.service;


import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.ArchiveWeightTargetAssessVO;
import net.edu.module.query.ArchiveWeightTargetAssessQuery;
import net.edu.module.entity.ArchiveWeightTargetAssessEntity;
import net.edu.module.vo.ArchiveWeightTargetKnowledgeVO;

import java.util.List;

/**
 * 考核点权重
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-29
 */
public interface ArchiveWeightTargetAssessService extends BaseService<ArchiveWeightTargetAssessEntity> {

    PageResult<ArchiveWeightTargetAssessVO> page(ArchiveWeightTargetAssessQuery query);

    void save(ArchiveWeightTargetAssessVO vo);

    void update(ArchiveWeightTargetAssessVO vo);

    void delete(List<Long> idList);

    List<ArchiveWeightTargetAssessVO> selectAssessByCourseId(Long courseId);

    Integer insertAssessWeight(List<ArchiveWeightTargetAssessVO> assessVOS);
}