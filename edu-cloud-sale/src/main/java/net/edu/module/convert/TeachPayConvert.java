package net.edu.module.convert;

import net.edu.module.entity.TeachPayEntity;
import net.edu.module.vo.TeachPayVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 流水账管理
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-09-16
*/
@Mapper
public interface TeachPayConvert {
    TeachPayConvert INSTANCE = Mappers.getMapper(TeachPayConvert.class);

    TeachPayEntity convert(TeachPayVO vo);

    TeachPayVO convert(TeachPayEntity entity);

    List<TeachPayVO> convertList(List<TeachPayEntity> list);

}