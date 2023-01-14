package net.edu.module.dao;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.LessonAttendLogEntity;
import net.edu.module.query.LessonAttendLogQuery;
import net.edu.module.vo.LessonAttendLogVO;
import net.edu.module.vo.StudentsStatisticsInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* 课堂签到表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-15
*/
@Mapper
public interface LessonAttendLogDao extends BaseDao<LessonAttendLogEntity> {

    void insertUserList(@Param("list") List<Long> list,Long lessonId);

    /**
     * 根据学生id批量插入课程id
     * @param list
     * @param stuId
     */
    void insertLessonList(@Param("list") List<Long> list,Long stuId);

    /**
     * 根据学生id批量退出课程
     */
    void deleteLessonList(@Param("list") List<Long> list,Long stuId);

    List<LessonAttendLogVO> selectStudentsList(@Param("query") LessonAttendLogQuery query);

    int updateStudents(@Param("vo") LessonAttendLogVO vo);

    int giveLikes(Long lessonId, Long stuId);

    int resetLikes(Long lessonId, Long stuId);

}