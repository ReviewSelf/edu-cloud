package net.edu.module.convert;

import net.edu.module.entity.TeachDestroyedLessonRecordEntity;
import net.edu.module.vo.TeachDestroyedLessonRecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 消课管理
*
* @author sqw 
* @since 1.0.0 2023-03-04
*/
@Mapper
public interface TeachDestroyedLessonRecordConvert {
    TeachDestroyedLessonRecordConvert INSTANCE = Mappers.getMapper(TeachDestroyedLessonRecordConvert.class);

    TeachDestroyedLessonRecordEntity convert(TeachDestroyedLessonRecordVO vo);

    TeachDestroyedLessonRecordVO convert(TeachDestroyedLessonRecordEntity entity);

    List<TeachDestroyedLessonRecordVO> convertList(List<TeachDestroyedLessonRecordEntity> list);

}