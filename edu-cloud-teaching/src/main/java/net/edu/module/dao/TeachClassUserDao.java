package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.TeachClassUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 班级用户表
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-09-16
*/
@Mapper
public interface TeachClassUserDao extends BaseDao<TeachClassUserEntity> {
	
}