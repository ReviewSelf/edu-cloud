package net.edu.module.dao;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.LessonEntity;
import net.edu.module.query.LessonQuery;
import net.edu.module.vo.LessonVO;
import net.edu.module.vo.WxWorkPublishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 课程表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-15
*/
@Mapper
public interface LessonDao extends BaseDao<LessonEntity> {
    IPage<LessonVO> selectStudentPage(Page<LessonVO> page, @Param("query") LessonQuery query);

    IPage<LessonVO> selectTeacherPage(Page<LessonVO> page, @Param("query")LessonQuery query);

    int updateHomework(@Param("vo") LessonVO vo);

    IPage<LessonVO> selectHomeworkPage(Page<LessonVO> page, @Param("query") LessonQuery query);


    List<LessonVO> getListById(Long classId);

    int deleteByClassId(Long classId);

    List<WxWorkPublishVO> selectHomeworkBegin(Long lessonId);

    int updateList(LessonVO vo);

    List<WxWorkDeadlineVO> selectHomeworkEnd(Long lessonId);
}
