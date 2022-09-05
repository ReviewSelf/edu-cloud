package net.edu.module.convert;

import net.edu.module.entity.FillProblemEntity;
import net.edu.module.vo.FillProblemVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 填空题表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-05
*/
@Mapper
public interface FillProblemConvert {
    FillProblemConvert INSTANCE = Mappers.getMapper(FillProblemConvert.class);

    FillProblemEntity convert(FillProblemVO vo);

    FillProblemVO convert(FillProblemEntity entity);

    List<FillProblemVO> convertList(List<FillProblemEntity> list);

}