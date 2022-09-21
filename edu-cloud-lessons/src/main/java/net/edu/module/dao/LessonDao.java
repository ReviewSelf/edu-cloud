package net.edu.module.dao;


import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.LessonEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 课程表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-15
*/
@Mapper
public interface LessonDao extends BaseDao<LessonEntity> {

}