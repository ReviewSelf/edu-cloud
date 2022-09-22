package net.edu.module.dao;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.LessonEntity;
import net.edu.module.query.LessonQuery;
import net.edu.module.vo.LessonVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* 课程表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-15
*/
@Mapper
public interface LessonDao extends BaseDao<LessonEntity> {

    IPage<LessonVO> selectStudentPage(Page<LessonVO> page, @Param("query")LessonQuery query);

    IPage<LessonVO> selectTeacherPage(Page<LessonVO> page, @Param("query")LessonQuery query);

}