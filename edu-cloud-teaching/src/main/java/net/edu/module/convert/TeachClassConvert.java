package net.edu.module.convert;

import net.edu.module.entity.TeachClassEntity;
import net.edu.module.vo.TeachClassVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 班级表
 *
 * @author wengruichen babamu@126.com
 * @since 1.0.0 2022-09-09
 */
@Mapper
public interface TeachClassConvert {
    TeachClassConvert INSTANCE = Mappers.getMapper(TeachClassConvert.class);

    TeachClassEntity convert(TeachClassVO vo);

    TeachClassVO convert(TeachClassEntity entity);

    List<TeachClassVO> convertList(List<TeachClassEntity> list);

}