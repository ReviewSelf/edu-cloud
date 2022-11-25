package net.edu.module.service.impl;


import lombok.AllArgsConstructor;
import net.edu.module.dao.StatisticsLessonInfoDao;
import net.edu.module.service.*;
import net.edu.module.vo.StudentsStatisticsInfoVO;
import net.edu.module.vo.TeacherStatisticsInfoVO;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/15 9:36
 * @Version: 1.0
 * @Description:
 */
@Service
@AllArgsConstructor
public class StatisticsLessonInfoServiceImpl implements StatisticsLessonInfoService {
    private final StatisticsLessonInfoDao statisticsLessonInfoDao;
    private final ExamAttendLogService examAttendLogService;

    @Override
    public Map<String ,String> getStudentHomeworkStatisticsInfo(Long userId){
        return statisticsLessonInfoDao.selectStudentHomeworkStatisticsInfo(userId);
    }

    @Override
    public Map<String ,String> getStudentLessonStatisticsInfo(Long userId) {
        return statisticsLessonInfoDao.selectStudentLessonStatisticsInfo(userId);
    }
    @Override
    public Map<String ,String> getStudentLessonProblemStatisticsInfo(Long userId){
        return statisticsLessonInfoDao.selectStudentLessonProblemStatisticsInfo(userId);
    }

    @Override
    public StudentsStatisticsInfoVO getStudentsStatisticsInfo(Long userId) {
        Map<String ,String> map1 = getStudentHomeworkStatisticsInfo(userId);
        Map<String ,String> map2 = examAttendLogService.getStudentExamStatisticsInfo(userId);
        Map<String ,String> map3 = getStudentLessonStatisticsInfo(userId);
        Map<String ,String> map4 = getStudentLessonProblemStatisticsInfo(userId);
        StudentsStatisticsInfoVO vo = new StudentsStatisticsInfoVO();
        if(map1 != null){
            vo.setHomeworkNum(Integer.valueOf(String.valueOf(map1.get("homeworkNum"))));
            vo.setHomeworkCompleteProblemNum(Integer.valueOf(String.valueOf(map1.get("homeworkCompleteProblemNum"))));
            vo.setHomeworkProblemNum(Integer.valueOf(String.valueOf(map1.get("homeworkProblemNum"))));
            vo.setHomeworkEndTime(String.valueOf(map1.get("homeworkEndTime")));
        }
        if(map2 !=null){
            vo.setPaperName(String.valueOf(map2.get("paperName")));
            vo.setUnpaidPapersNum(Integer.valueOf(String.valueOf(map2.get("unpaidPapersNum"))));
            vo.setPapersEndTime(String.valueOf(map2.get("papersEndTime")));
        }
        if(map3!=null){
            vo.setLessonNum(Integer.valueOf(String.valueOf(map3.get("lessonNum"))));
            vo.setLessonBeginTime(String.valueOf(map3.get("lessonBeginTime")));
            vo.setUnpaidLessonNum(Integer.valueOf(String.valueOf(map3.get("unpaidLessonNum"))));
        }
        if(map4!=null){
            vo.setUnpaidClassProblemNum(Integer.valueOf(String.valueOf(map4.get("unpaidClassProblemNum"))));
        }
        return vo;
    }

    @Override
    public TeacherStatisticsInfoVO getTeacherStatisticsInfo(Long userId) {
        Map<String ,String> map1 = getTeacherHomeworkStatisticsInfo(userId);
        Map<String ,String> map2 = examAttendLogService.getTeacherExamStatisticsInfo(userId);
        Map<String ,String> map3 = getTeacherLessonStatisticsInfo(userId);
        TeacherStatisticsInfoVO vo = new TeacherStatisticsInfoVO();
        if(map1 != null){
            vo.setHomeworkNum(Integer.valueOf(String.valueOf(map1.get("homeworkNum"))));
            vo.setHomeworkName(String.valueOf(map1.get("homeworkName")));
            vo.setHomeworkEndTime(String.valueOf(map1.get("homeworkEndTime")));
        }
        if(map2 !=null){
            vo.setPaperNum(Integer.valueOf(String.valueOf(map2.get("paperNum"))));
            vo.setPaperName(String.valueOf(map2.get("paperName")));
            vo.setPapersBeginTime(String.valueOf(map2.get("papersBeginTime")));
        }
        if(map3!=null){
            vo.setLessonNum(Integer.valueOf(String.valueOf(map3.get("lessonNum"))));
            vo.setLessonBeginTime(String.valueOf(map3.get("lessonBeginTime")));
            vo.setLessonName(String.valueOf(map3.get("lessonName")));
        }

        return vo;
    }

    private Map<String, String> getTeacherLessonStatisticsInfo(Long userId) {
        return statisticsLessonInfoDao.selectTeacherLessonStatisticsInfo(userId);
    }

    private Map<String, String> getTeacherHomeworkStatisticsInfo(Long userId) {
        return statisticsLessonInfoDao.selectTeacherHomeworkStatisticsInfo(userId);
    }

}
