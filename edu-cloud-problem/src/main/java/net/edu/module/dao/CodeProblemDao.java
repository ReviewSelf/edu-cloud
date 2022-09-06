package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.CodeProblemEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 代码题库表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-05
*/
@Mapper
public interface CodeProblemDao extends BaseDao<CodeProblemEntity> {
	
}