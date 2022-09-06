package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.FillProblemEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 填空题表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-05
*/
@Mapper
public interface FillProblemDao extends BaseDao<FillProblemEntity> {
	
}