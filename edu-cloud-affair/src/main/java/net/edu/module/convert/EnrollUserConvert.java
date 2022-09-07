package net.edu.module.convert;

import net.edu.module.entity.EnrollUserEntity;
import net.edu.module.vo.EnrollUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* XinXiHeShi
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-09-05
*/
@Mapper
public interface EnrollUserConvert {
    EnrollUserConvert INSTANCE = Mappers.getMapper(EnrollUserConvert.class);

    EnrollUserEntity convert(EnrollUserVO vo);

    EnrollUserVO convert(EnrollUserEntity entity);

    List<EnrollUserVO> convertList(List<EnrollUserEntity> list);

}
