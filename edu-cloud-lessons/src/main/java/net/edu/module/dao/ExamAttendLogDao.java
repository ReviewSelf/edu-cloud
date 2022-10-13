package net.edu.module.dao;


import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ExamAttendLogEntity;
import net.edu.module.query.ExamAttendLogQuery;
import net.edu.module.vo.ExamAttendLogVO;
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
public interface ExamAttendLogDao extends BaseDao<ExamAttendLogEntity> {

    void insertUserList(@Param("list") List<Long> list,Long lessonId);

    /**
     * 根据学生id批量插入课程id
     * @param list
     * @param stuId
     */
    void insertExamList(@Param("list") List<Long> list,Long stuId);

    /**
     * 根据学生id批量退出课程
     */
    void deleteExamList(@Param("list") List<Long> list,Long stuId);

    List<ExamAttendLogVO> selectStudentsList(@Param("query") ExamAttendLogQuery query);

    int updateStudents(@Param("vo") ExamAttendLogVO vo);
}