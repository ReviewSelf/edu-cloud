package net.edu.system.convert;

import net.edu.system.entity.SysRoleEntity;
import net.edu.system.vo.SysRoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysRoleConvert {
    SysRoleConvert INSTANCE = Mappers.getMapper(SysRoleConvert.class);

    SysRoleVO convert(SysRoleEntity entity);

    SysRoleEntity convert(SysRoleVO vo);
    
    List<SysRoleVO> convertList(List<SysRoleEntity> list);

}
