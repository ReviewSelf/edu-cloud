package net.edu.module.service;


import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.ArchiveWeightTargetKnowledgeVO;
import net.edu.module.query.ArchiveWeightTargetKnowledgeQuery;
import net.edu.module.entity.ArchiveWeightTargetKnowledgeEntity;

import java.util.List;

/**
 * 一级知识点权重
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-29
 */
public interface ArchiveWeightTargetKnowledgeService extends BaseService<ArchiveWeightTargetKnowledgeEntity> {

    PageResult<ArchiveWeightTargetKnowledgeVO> page(ArchiveWeightTargetKnowledgeQuery query);

    void save(ArchiveWeightTargetKnowledgeVO vo);

    void update(ArchiveWeightTargetKnowledgeVO vo);

    void delete(Long targetId,Long courseId);

    List<ArchiveWeightTargetKnowledgeVO> selectKnowledgeByTargetId(Long targetId);

    Integer insertKnowledgeWeight(List<ArchiveWeightTargetKnowledgeVO> archiveWeightTargetKnowledgeVO);



}
