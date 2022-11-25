package net.edu.module.convert;


import net.edu.module.entity.ArchiveSignEntity;
import net.edu.module.vo.ArchiveSignVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 平时记录
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-23
*/
@Mapper
public interface ArchiveSignConvert {
    ArchiveSignConvert INSTANCE = Mappers.getMapper(ArchiveSignConvert.class);

    ArchiveSignEntity convert(ArchiveSignVO vo);

    ArchiveSignVO convert(ArchiveSignEntity entity);

    List<ArchiveSignVO> convertList(List<ArchiveSignEntity> list);

}
