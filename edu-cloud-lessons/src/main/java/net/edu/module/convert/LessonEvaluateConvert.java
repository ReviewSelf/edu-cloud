package net.edu.module.convert;

import net.edu.module.entity.LessonEvaluateEntity;
import net.edu.module.vo.LessonEvaluateVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 课堂评价
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-18
*/
@Mapper
public interface LessonEvaluateConvert {
    LessonEvaluateConvert INSTANCE = Mappers.getMapper(LessonEvaluateConvert.class);

    LessonEvaluateEntity convert(LessonEvaluateVO vo);

    LessonEvaluateVO convert(LessonEvaluateEntity entity);

    List<LessonEvaluateVO> convertList(List<LessonEvaluateEntity> list);

}