package net.edu.module.convert;

import net.edu.module.entity.OrgEntity;
import net.edu.module.vo.OrgVo;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrgConvert {
    OrgConvert INSTANCE = Mappers.getMapper(OrgConvert.class);
    OrgEntity convert(OrgVo vo);

    OrgVo convert(OrgEntity entity);
    List<OrgVo> convertList(List<OrgEntity> list);
}
