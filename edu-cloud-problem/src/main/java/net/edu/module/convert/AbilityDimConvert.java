package net.edu.module.convert;

import net.edu.module.entity.AbilityDimEntity;
import net.edu.module.vo.AbilityDimVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 能力模块表
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-25
*/
@Mapper
public interface AbilityDimConvert {
    AbilityDimConvert INSTANCE = Mappers.getMapper(AbilityDimConvert.class);

    AbilityDimEntity convert(AbilityDimVO vo);

    AbilityDimVO convert(AbilityDimEntity entity);

    List<AbilityDimVO> convertList(List<AbilityDimEntity> list);

}