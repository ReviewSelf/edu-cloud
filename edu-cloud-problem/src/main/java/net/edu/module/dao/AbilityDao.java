package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.AbilityEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 能力纬度图
*
* @author sqw 
* @since 1.0.0 2022-10-27
*/
@Mapper
public interface AbilityDao extends BaseDao<AbilityEntity> {
	
}