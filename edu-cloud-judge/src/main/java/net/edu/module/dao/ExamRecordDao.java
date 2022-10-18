package net.edu.module.dao;

import net.edu.module.vo.exam.ExamScoreVO;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/18 9:39
 * @Version: 1.0
 * @Description:
 */
@Mapper
public interface ExamRecordDao {
   List<ExamScoreVO> selectExamScore(Long examId, Long userId,Integer status,Integer isCorrect);
}
