package net.edu.module.convert;

import net.edu.module.entity.ArchiveCourseEntity;
import net.edu.module.vo.ArchiveCourseVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 能力课程
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-25
*/
@Mapper
public interface ArchiveCourseConvert {
    ArchiveCourseConvert INSTANCE = Mappers.getMapper(ArchiveCourseConvert.class);

    ArchiveCourseEntity convert(ArchiveCourseVO vo);

    ArchiveCourseVO convert(ArchiveCourseEntity entity);

    List<ArchiveCourseVO> convertList(List<ArchiveCourseEntity> list);

}