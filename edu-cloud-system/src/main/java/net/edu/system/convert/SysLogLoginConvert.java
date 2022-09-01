package net.edu.system.convert;

import net.edu.system.entity.SysLogLoginEntity;
import net.edu.system.vo.SysLogLoginVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 登录日志
 *
 * @author 阿沐 babamu@126.com
 */
@Mapper
public interface SysLogLoginConvert {
    SysLogLoginConvert INSTANCE = Mappers.getMapper(SysLogLoginConvert.class);

    SysLogLoginEntity convert(SysLogLoginVO vo);

    SysLogLoginVO convert(SysLogLoginEntity entity);

    List<SysLogLoginVO> convertList(List<SysLogLoginEntity> list);

}