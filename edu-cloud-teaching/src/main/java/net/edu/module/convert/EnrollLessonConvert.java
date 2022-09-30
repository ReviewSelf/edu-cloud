package net.edu.module.convert;

import net.edu.module.entity.EnrollLessonEntity;
import net.edu.module.vo.EnrollLessonVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnrollLessonConvert {

    EnrollLessonConvert INSTANCE = Mappers.getMapper(EnrollLessonConvert.class);

    EnrollLessonEntity convert(EnrollLessonVO vo);

    EnrollLessonVO convert(EnrollLessonEntity entity);
}