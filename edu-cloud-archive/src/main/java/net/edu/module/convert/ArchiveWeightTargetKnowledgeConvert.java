package net.edu.module.convert;

import net.edu.module.entity.ArchiveWeightTargetKnowledgeEntity;
import net.edu.module.vo.ArchiveWeightTargetKnowledgeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 一级知识点权重
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-29
*/
@Mapper
public interface ArchiveWeightTargetKnowledgeConvert {
    ArchiveWeightTargetKnowledgeConvert INSTANCE = Mappers.getMapper(ArchiveWeightTargetKnowledgeConvert.class);

    ArchiveWeightTargetKnowledgeEntity convert(ArchiveWeightTargetKnowledgeVO vo);

    ArchiveWeightTargetKnowledgeVO convert(ArchiveWeightTargetKnowledgeEntity entity);

    List<ArchiveWeightTargetKnowledgeVO> convertList(List<ArchiveWeightTargetKnowledgeEntity> list);

}