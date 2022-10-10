package net.edu.module.convert;

import net.edu.module.entity.ExamEntity;
import net.edu.module.vo.ExamVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 考试
*
* @author 小樊 babamu@126.com
* @since 1.0.0 2022-10-09
*/
@Mapper
public interface ExamConvert {
    ExamConvert INSTANCE = Mappers.getMapper(ExamConvert.class);

    ExamEntity convert(ExamVO vo);

    ExamVO convert(ExamEntity entity);

    List<ExamVO> convertList(List<ExamEntity> list);

}