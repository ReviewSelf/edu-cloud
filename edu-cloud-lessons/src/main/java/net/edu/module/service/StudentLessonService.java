package net.edu.module.service;

import net.edu.framework.common.utils.Result;
import net.edu.module.vo.ExamPaperVo;
import net.edu.module.vo.StudentsStatisticsInfoVO;

public interface StudentLessonService {


    Result<String> attendLesson(Long lessonId);

    Result<Object> attendExam(Long examId);

    ExamPaperVo getStuExamInfo(Long examId,Long userId);

}
