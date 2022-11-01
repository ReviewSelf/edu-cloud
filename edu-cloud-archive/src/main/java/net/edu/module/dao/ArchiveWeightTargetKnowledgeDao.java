package net.edu.module.dao;


import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ArchiveWeightTargetKnowledgeEntity;
import net.edu.module.vo.ArchiveWeightTargetKnowledgeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* 一级知识点权重
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-29
*/
@Mapper
public interface ArchiveWeightTargetKnowledgeDao extends BaseDao<ArchiveWeightTargetKnowledgeEntity> {

    List<ArchiveWeightTargetKnowledgeVO> selectKnowledgeByTargetId(Long targetId);

    Integer insertKnowledgeWeight(List<ArchiveWeightTargetKnowledgeVO> archiveWeightTargetKnowledgeVO);
}
