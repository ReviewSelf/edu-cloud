package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.LessonResourceEntity;
import net.edu.module.vo.LessonResourceVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* 教学日历资源表
*
* @author 马佳浩 babamu@126.com
* @since 1.0.0 2022-09-15
*/
@Mapper
public interface LessonResourceDao extends BaseDao<LessonResourceEntity> {
    List<LessonResourceVO> selectLessonResource(Long lessonId);
	
}