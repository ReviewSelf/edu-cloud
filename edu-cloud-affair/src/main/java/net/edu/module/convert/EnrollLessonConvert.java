package net.edu.module.convert;

import net.edu.module.entity.EnrollLessonEntity;
import net.edu.module.entity.EnrollUserEntity;
import net.edu.module.vo.EnrollLessonVO;
import net.edu.module.vo.EnrollUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EnrollLessonConvert {

    EnrollLessonConvert INSTANCE = Mappers.getMapper(EnrollLessonConvert.class);

    EnrollLessonEntity convert(EnrollLessonVO vo);
}