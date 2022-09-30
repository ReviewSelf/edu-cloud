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

    JudgeSampleResultVO selectUpdateRecord(Long recordId);
    int updateRecord(JudgeSampleResultVO vo);

    JudgeRecordSubmitVO selectResult(Long recordId);

    JudgeRecordSubmitVO selectRecord(JudgeRecordSubmitVO vo);

    List<LessonJudgeRecordVo> selectLessonRecord(Long lessonId,Integer type);

    JudgeRecordSubmitVO selectRecordAndAnswer(JudgeRecordSubmitVO vo);

    int updateReasonAndStatus(JudgeRecordSubmitVO vo);
}
