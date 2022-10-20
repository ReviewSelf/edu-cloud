package net.edu.ability.convert;

import net.edu.ability.entity.GraduateRequireEntity;
import net.edu.ability.vo.GraduateRequireVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 毕业要求
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-20
*/
@Mapper
public interface GraduateRequireConvert {
    GraduateRequireConvert INSTANCE = Mappers.getMapper(GraduateRequireConvert.class);

    GraduateRequireEntity convert(GraduateRequireVO vo);

    GraduateRequireVO convert(GraduateRequireEntity entity);

    List<GraduateRequireVO> convertList(List<GraduateRequireEntity> list);

}