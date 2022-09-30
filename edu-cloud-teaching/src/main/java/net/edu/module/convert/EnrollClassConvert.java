package net.edu.module.convert;

import net.edu.module.entity.EnrollClassEntity;
import net.edu.module.vo.EnrollClassVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
* 班级发布
*
* @author 翁瑞辰
* @since  2022-09-06
*/
@Mapper
public interface EnrollClassConvert {
    EnrollClassConvert INSTANCE = Mappers.getMapper(EnrollClassConvert.class);

    EnrollClassEntity convert(EnrollClassVO vo);

    EnrollClassVO convert(EnrollClassEntity entity);

}
