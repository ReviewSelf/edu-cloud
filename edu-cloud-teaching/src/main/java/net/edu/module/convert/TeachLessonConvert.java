package net.edu.module.convert;

import net.edu.module.entity.TeachLessonEntity;
import net.edu.module.vo.TeachLessonVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 课程表
*
* @author 翁瑞辰 babamu@126.com
* @since 1.0.0 2022-09-09
*/
@Mapper
public interface TeachLessonConvert {
    TeachLessonConvert INSTANCE = Mappers.getMapper(TeachLessonConvert.class);

    TeachLessonEntity convert(TeachLessonVO vo);

    TeachLessonVO convert(TeachLessonEntity entity);

    List<TeachLessonVO> convertList(List<TeachLessonEntity> list);

}