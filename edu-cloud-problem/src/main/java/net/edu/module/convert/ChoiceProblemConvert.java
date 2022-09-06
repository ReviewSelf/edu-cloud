package net.edu.module.convert;

import net.edu.module.entity.ChoiceProblemEntity;
import net.edu.module.vo.ChoiceProblemVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 选择题库表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-05
*/
@Mapper
public interface ChoiceProblemConvert {
    ChoiceProblemConvert INSTANCE = Mappers.getMapper(ChoiceProblemConvert.class);

    ChoiceProblemEntity convert(ChoiceProblemVO vo);

    ChoiceProblemVO convert(ChoiceProblemEntity entity);

    List<ChoiceProblemVO> convertList(List<ChoiceProblemEntity> list);

}