package net.edu.module.convert;

import net.edu.module.entity.ArchiveAssessEntity;
import net.edu.module.vo.ArchiveAssessVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 考核点
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-26
*/
@Mapper
public interface ArchiveAssessConvert {
    ArchiveAssessConvert INSTANCE = Mappers.getMapper(ArchiveAssessConvert.class);

    ArchiveAssessEntity convert(ArchiveAssessVO vo);

    ArchiveAssessVO convert(ArchiveAssessEntity entity);

    List<ArchiveAssessVO> convertList(List<ArchiveAssessEntity> list);

}