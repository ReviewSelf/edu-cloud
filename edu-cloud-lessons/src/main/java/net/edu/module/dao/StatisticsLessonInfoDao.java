package net.edu.module.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
* 课堂签到表
*
* @author sqw 
* @since 1.0.0 2022-11-24
*/
@Mapper
public interface StatisticsLessonInfoDao {
    

    Map<String ,String> selectStudentHomeworkStatisticsInfo(Long userId);

    Map<String ,String> selectStudentLessonStatisticsInfo(Long userId);

    Map<String ,String> selectStudentLessonProblemStatisticsInfo(Long userId);

    Map<String, String> selectTeacherLessonStatisticsInfo(Long userId);

    Map<String, String> selectTeacherHomeworkStatisticsInfo(Long userId);
}