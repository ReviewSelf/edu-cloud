package net.edu.module.convert;

import net.edu.module.entity.TeachPlanEntity;
import net.edu.module.vo.TeachPlanVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 教学计划表
*
* @author sqw 
* @since 1.0.0 2022-09-12
*/
@Mapper
public interface TeachPlanConvert {
    TeachPlanConvert INSTANCE = Mappers.getMapper(TeachPlanConvert.class);

    TeachPlanEntity convert(TeachPlanVO vo);

    TeachPlanVO convert(TeachPlanEntity entity);

    List<TeachPlanVO> convertList(List<TeachPlanEntity> list);

}