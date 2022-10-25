package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ArchiveCourseEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 能力课程
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-25
*/
@Mapper
public interface ArchiveCourseDao extends BaseDao<ArchiveCourseEntity> {
	
}