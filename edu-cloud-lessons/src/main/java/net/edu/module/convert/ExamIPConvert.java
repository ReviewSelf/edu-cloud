package net.edu.module.convert;

import net.edu.module.entity.ExamIPEntity;
import net.edu.module.vo.ExamIPVO;
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
public interface ExamIPConvert {
    ExamIPConvert INSTANCE = Mappers.getMapper(ExamIPConvert.class);

    ExamIPEntity convert(ExamIPVO vo);

    ExamIPVO convert(ExamIPEntity entity);

    List<ExamIPVO> convertList(List<ExamIPEntity> list);

}