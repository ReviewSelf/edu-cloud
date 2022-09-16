package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.LessonProblemEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 课堂练习表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-15
*/
@Mapper
public interface LessonProblemDao extends BaseDao<LessonProblemEntity> {
	
}