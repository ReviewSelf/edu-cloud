package net.edu.module.convert;


import net.edu.module.entity.ExamProblemEntity;
import net.edu.module.vo.ExamProblemVO;
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
public interface ExamProblemConvert {
    ExamProblemConvert INSTANCE = Mappers.getMapper(ExamProblemConvert.class);

    ExamProblemEntity convert(ExamProblemVO vo);


    ExamProblemVO convert(ExamProblemEntity entity);

    List<ExamProblemVO> convertList(List<ExamProblemEntity> list);

}