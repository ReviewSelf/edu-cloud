package net.edu.module.convert;

import net.edu.module.entity.AbilityEntity;
import net.edu.module.vo.AbilityVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 能力纬度图
*
* @author sqw 
* @since 1.0.0 2022-10-27
*/
@Mapper
public interface AbilityConvert {
    AbilityConvert INSTANCE = Mappers.getMapper(AbilityConvert.class);

    AbilityEntity convert(AbilityVO vo);

    AbilityVO convert(AbilityEntity entity);

    List<AbilityVO> convertList(List<AbilityEntity> list);

}