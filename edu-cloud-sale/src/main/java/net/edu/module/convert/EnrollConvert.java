package net.edu.module.convert;

import net.edu.module.entity.EnrollEntity;
import net.edu.module.vo.EnrollUserVO;
import net.edu.module.vo.EnrollVO;
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
public interface EnrollConvert {
    EnrollConvert INSTANCE = Mappers.getMapper(EnrollConvert.class);

    EnrollEntity convert(EnrollVO vo);

    EnrollVO convert(EnrollEntity entity);

    List<EnrollVO> convertList(List<EnrollEntity> list);



}
