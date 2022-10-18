package net.edu.module.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.edu.module.dao.ExamRecordDao;
import net.edu.module.dao.LessonRecordDao;
import net.edu.module.vo.exam.ExamScoreVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/18 9:38
 * @Version: 1.0
 * @Description:
 */
@Service
@AllArgsConstructor
@Slf4j
public class ExamRecordService {
    private final ExamRecordDao examRecordDao;

    public List<ExamScoreVO> getExamScore(Long examId, Long userId,Integer status,Integer isCorrect){
        List<ExamScoreVO> list=examRecordDao.selectExamScore(examId,userId,status,isCorrect);
       return list;
    }
}
