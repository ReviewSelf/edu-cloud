package net.edu.module.convert;

import net.edu.module.entity.ArchiveTestScoreEntity;
import net.edu.module.vo.ArchiveTestScoreVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 考试成绩表
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-16
*/
@Mapper
public interface ArchiveTestScoreConvert {
    ArchiveTestScoreConvert INSTANCE = Mappers.getMapper(ArchiveTestScoreConvert.class);

//    ArchiveTestScoreEntity convert(ArchiveTestScoreVO vo);

//    ArchiveTestScoreVO convert(ArchiveTestScoreEntity entity);

//    List<ArchiveTestScoreVO> convertList(List<ArchiveTestScoreEntity> list);

}