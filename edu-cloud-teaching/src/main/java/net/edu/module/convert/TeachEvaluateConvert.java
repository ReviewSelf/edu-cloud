package net.edu.module.convert;

import net.edu.module.entity.TeachEvaluateEntity;
import net.edu.module.vo.TeachEvaluateVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 教学评价
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-08
*/
@Mapper
public interface TeachEvaluateConvert {
    TeachEvaluateConvert INSTANCE = Mappers.getMapper(TeachEvaluateConvert.class);

    TeachEvaluateEntity convert(TeachEvaluateVO vo);

    TeachEvaluateVO convert(TeachEvaluateEntity entity);

    List<TeachEvaluateVO> convertList(List<TeachEvaluateEntity> list);

}