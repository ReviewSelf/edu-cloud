package net.edu.module.convert;

import net.edu.module.entity.TeachPlanItemEntity;
import net.edu.module.vo.TeachPlanItemVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 教学日历表
*
* @author sqw 
* @since 1.0.0 2022-09-12
*/
@Mapper
public interface TeachPlanItemConvert {
    TeachPlanItemConvert INSTANCE = Mappers.getMapper(TeachPlanItemConvert.class);

    TeachPlanItemEntity convert(TeachPlanItemVO vo);

    TeachPlanItemVO convert(TeachPlanItemEntity entity);

    List<TeachPlanItemVO> convertList(List<TeachPlanItemEntity> list);

}