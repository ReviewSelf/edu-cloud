package net.edu.module.convert;

import net.edu.module.api.vo.ProblemPaperItemEntity;
import net.edu.module.entity.LessonProblemEntity;
import net.edu.module.vo.LessonProblemVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 课堂练习表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-15
*/
@Mapper
public interface LessonProblemConvert {
    LessonProblemConvert INSTANCE = Mappers.getMapper(LessonProblemConvert.class);

    LessonProblemEntity convert(LessonProblemVO vo);


    LessonProblemVO convert(LessonProblemEntity entity);

    List<LessonProblemVO> convertList(List<LessonProblemEntity> list);

}