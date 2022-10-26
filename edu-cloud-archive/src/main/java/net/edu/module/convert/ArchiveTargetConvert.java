package net.edu.module.convert;

import net.edu.module.entity.ArchiveTargetEntity;
import net.edu.module.vo.ArchiveTargetVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* target
*
* @author qxd babamu@126.com
* @since 1.0.0 2022-10-24
*/
@Mapper
public interface ArchiveTargetConvert {
    ArchiveTargetConvert INSTANCE = Mappers.getMapper(ArchiveTargetConvert.class);

    ArchiveTargetEntity convert(ArchiveTargetVO vo);

    ArchiveTargetVO convert(ArchiveTargetEntity entity);

    List<ArchiveTargetVO> convertList(List<ArchiveTargetEntity> list);

}
