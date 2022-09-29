package net.edu.module.dao;

import net.edu.module.vo.*;
import org.apache.ibatis.annotations.Mapper;

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

    JudgeResultVO selectUpdateRecord(Long recordId);
    int updateRecord(JudgeResultVO vo);

    JudgeRecordSubmitVO selectResult(Long recordId);

    JudgeRecordSubmitVO selectRecord(JudgeRecordSubmitVO vo);

    List<LessonJudgeRecordVo> selectLessonRecord(Long lessonId,Integer type);

    ProblemCompletionVo selectRecordAndAnswer(ProblemCompletionVo vo);

    int updateReasonAndStatus(ProblemCompletionVo vo);
}
