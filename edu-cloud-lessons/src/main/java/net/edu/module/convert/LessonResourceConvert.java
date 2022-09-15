package net.edu.module.convert;

import net.edu.module.entity.LessonResourceEntity;
import net.edu.module.vo.LessonResourceVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 教学日历资源表
*
* @author 马佳浩 babamu@126.com
* @since 1.0.0 2022-09-15
*/
@Mapper
public interface LessonResourceConvert {
    LessonResourceConvert INSTANCE = Mappers.getMapper(LessonResourceConvert.class);

    LessonResourceEntity convert(LessonResourceVO vo);

    LessonResourceVO convert(LessonResourceEntity entity);

    List<LessonResourceVO> convertList(List<LessonResourceEntity> list);

}