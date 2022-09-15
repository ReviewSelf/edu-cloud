package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.TeachLessonEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 课程表
*
* @author 翁瑞辰 babamu@126.com
* @since 1.0.0 2022-09-09
*/
@Mapper
public interface TeachLessonDao extends BaseDao<TeachLessonEntity> {
	
}