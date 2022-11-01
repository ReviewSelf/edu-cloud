package net.edu.module.convert;

import net.edu.module.entity.ArchiveWeightTargetAssessEntity;
import net.edu.module.vo.ArchiveWeightTargetAssessVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 考核点权重
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-29
*/
@Mapper
public interface ArchiveWeightTargetAssessConvert {
    ArchiveWeightTargetAssessConvert INSTANCE = Mappers.getMapper(ArchiveWeightTargetAssessConvert.class);

    ArchiveWeightTargetAssessEntity convert(ArchiveWeightTargetAssessVO vo);

    ArchiveWeightTargetAssessVO convert(ArchiveWeightTargetAssessEntity entity);

    List<ArchiveWeightTargetAssessVO> convertList(List<ArchiveWeightTargetAssessEntity> list);

}