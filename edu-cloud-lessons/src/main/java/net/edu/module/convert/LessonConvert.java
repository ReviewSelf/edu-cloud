package net.edu.module.convert;

import net.edu.module.entity.LessonEntity;
import net.edu.module.vo.LessonVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 课程表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-15
*/
@Mapper
public interface LessonConvert {
    LessonConvert INSTANCE = Mappers.getMapper(LessonConvert.class);

    LessonEntity convert(LessonVO vo);

    LessonVO convert(LessonEntity entity);

    List<LessonVO> convertList(List<LessonEntity> list);

}