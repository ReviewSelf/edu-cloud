package net.edu.module.convert;

import net.edu.module.entity.ArchiveCourseSummaryEntity;
import net.edu.module.vo.ArchiveCourseSummaryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 课程总结
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-14
*/
@Mapper
public interface ArchiveCourseSummaryConvert {
    ArchiveCourseSummaryConvert INSTANCE = Mappers.getMapper(ArchiveCourseSummaryConvert.class);

    ArchiveCourseSummaryEntity convert(ArchiveCourseSummaryVO vo);

    ArchiveCourseSummaryVO convert(ArchiveCourseSummaryEntity entity);

    List<ArchiveCourseSummaryVO> convertList(List<ArchiveCourseSummaryEntity> list);

}