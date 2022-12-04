package net.edu.module.dao;

import net.edu.module.vo.*;
import net.edu.module.vo.lesson.LessonJudgeRecordVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Author: 马佳浩
 * @Date: 2022/9/12 12:57
 * @Version: 1.0
 * @Description:
 */
@Mapper
public interface JudgeRecordDao {

    int insertSubmitRecord(JudgeRecordSubmitVO vo);

    CodeProblemSubmitVO selectCodeProblemSubmit(Long recordId);

    JudgeSampleResultVO selectUpdateRecord(Long recordId);
    int updateRecord(JudgeSampleResultVO vo);

    JudgeRecordSubmitVO selectResult(Long recordId);

    JudgeRecordSubmitVO selectRecord(JudgeRecordSubmitVO vo);



    JudgeRecordSubmitVO selectRecordAndAnswer(JudgeRecordSubmitVO vo);

    int updateReasonAndStatus(JudgeRecordSubmitVO vo);

    int statisticsUserRecord(Long problemId ,Integer type,Long userId);

    int selectUserRecord(Long problemId ,Integer type,Long userId);

    int statisticsUserJudgeRecord(Long userId);

    int deleteStatisticsUserRecord(Long userId);

    List<String> selectFilePath(Long problemId,Integer problemType,Integer source,Long sourceId);
}
