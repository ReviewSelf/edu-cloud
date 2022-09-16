package net.edu.module.dao;


import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.TeachPayEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 流水账管理
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-09-16
*/
@Mapper
public interface TeachPayDao extends BaseDao<TeachPayEntity> {
	
}