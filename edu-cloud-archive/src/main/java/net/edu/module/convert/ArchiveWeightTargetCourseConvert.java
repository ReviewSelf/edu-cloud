package net.edu.module.convert;

import net.edu.module.entity.ArchiveWeightTargetCourseEntity;
import net.edu.module.vo.ArchiveWeightTargetCourseVO;
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
public interface ArchiveWeightTargetCourseConvert {
    ArchiveWeightTargetCourseConvert INSTANCE = Mappers.getMapper(ArchiveWeightTargetCourseConvert.class);

    ArchiveWeightTargetCourseEntity convert(ArchiveWeightTargetCourseVO vo);

    ArchiveWeightTargetCourseVO convert(ArchiveWeightTargetCourseEntity entity);

    List<ArchiveWeightTargetCourseVO> convertList(List<ArchiveWeightTargetCourseEntity> list);

}