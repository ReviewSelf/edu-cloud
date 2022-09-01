package net.edu.system.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.system.entity.SysPostEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 岗位管理
*
* @author 阿沐 babamu@126.com
*/
@Mapper
public interface SysPostDao extends BaseDao<SysPostEntity> {
	
}