package net.edu.module.convert;

import net.edu.module.entity.LessonIPEntity;
import net.edu.module.vo.LessonIPVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 1
*
* @author 马佳浩 
* @since 1.0.0 2022-09-15
*/
@Mapper
public interface LessonIPConvert {
    LessonIPConvert INSTANCE = Mappers.getMapper(LessonIPConvert.class);

    LessonIPEntity convert(LessonIPVO vo);

    LessonIPVO convert(LessonIPEntity entity);

    List<LessonIPVO> convertList(List<LessonIPEntity> list);

}