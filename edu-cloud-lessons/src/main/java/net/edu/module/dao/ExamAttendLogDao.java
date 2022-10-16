package net.edu.module.dao;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ExamAttendLogEntity;
import net.edu.module.query.ExamAttendLogQuery;
import net.edu.module.query.ExamQuery;
import net.edu.module.vo.ExamAttendLogVO;
import net.edu.module.vo.ExamVO;
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

    IPage<ExamAttendLogVO> page(Page<ExamAttendLogVO> page, @Param("query") ExamAttendLogQuery query);
    ExamAttendLogVO selectUserAttendById(Long userId,Long examId);

    List<ExamAttendLogVO> selectUserAttend(Long examId);



//
//    void insertUserList(@Param("list") List<Long> list,Long lessonId);
//
//
//
//
//    List<ExamAttendLogVO> selectStudentsList(@Param("query") ExamAttendLogQuery query);
//
//    int updateStudents(@Param("vo") ExamAttendLogVO vo);

    int insertAttendLogFromClass(@Param("userList") List<Long> userList,Long examId);
}