package net.edu.module.dao;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.LessonAttendLogEntity;
import net.edu.module.query.LessonAttendLogQuery;
import net.edu.module.vo.LessonAttendLogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 课堂签到表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-15
*/
@Mapper
public interface LessonAttendLogDao extends BaseDao<LessonAttendLogEntity> {

    void insertUserList(@Param("list") List<Long> list,Long lessonId);

    List<LessonAttendLogVO> selectStudentsList(@Param("query") LessonAttendLogQuery query);

    int updateStudents(@Param("vo") LessonAttendLogVO vo);
}