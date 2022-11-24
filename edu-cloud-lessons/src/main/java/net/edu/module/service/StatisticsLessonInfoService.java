package net.edu.module.service;

import net.edu.module.vo.StudentsStatisticsInfoVO;
import net.edu.module.vo.TeacherStatisticsInfoVO;

import java.util.Map;

public interface StatisticsLessonInfoService {
    StudentsStatisticsInfoVO getStudentsStatisticsInfo(Long userId);

    Map<String ,String> getStudentHomeworkStatisticsInfo(Long userId);

    Map<String ,String> getStudentLessonStatisticsInfo(Long userId);

    Map<String ,String> getStudentLessonProblemStatisticsInfo(Long userId);

    TeacherStatisticsInfoVO getTeacherStatisticsInfo(Long userId);
}
