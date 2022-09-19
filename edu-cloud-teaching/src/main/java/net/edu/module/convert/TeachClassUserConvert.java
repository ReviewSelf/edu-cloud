package net.edu.module.convert;

import net.edu.module.entity.TeachClassUserEntity;
import net.edu.module.vo.TeachClassUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 班级用户表
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-09-16
*/
@Mapper
public interface TeachClassUserConvert {
    TeachClassUserConvert INSTANCE = Mappers.getMapper(TeachClassUserConvert.class);

    TeachClassUserEntity convert(TeachClassUserVO vo);

    TeachClassUserVO convert(TeachClassUserEntity entity);

    List<TeachClassUserVO> convertList(List<TeachClassUserEntity> list);



}