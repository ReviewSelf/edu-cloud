package net.edu.module.convert;


import net.edu.module.entity.ArchiveExamEntity;
import net.edu.module.vo.ArchiveExamVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 123
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-11
*/
@Mapper
public interface ArchiveExamConvert {
    ArchiveExamConvert INSTANCE = Mappers.getMapper(ArchiveExamConvert.class);

    ArchiveExamEntity convert(ArchiveExamVO vo);

    ArchiveExamVO convert(ArchiveExamEntity entity);

    List<ArchiveExamVO> convertList(List<ArchiveExamEntity> list);

}