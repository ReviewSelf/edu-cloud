package net.edu.module.service;

import lombok.AllArgsConstructor;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.dao.JudgeRecordDao;
import net.edu.module.vo.JudgeRecordSubmitVO;
import net.edu.module.vo.LessonJudgeRecordVo;
import net.edu.module.vo.ProblemCompletionVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/22 11:03
 * @Version: 1.0
 * @Description:
 */
@Service
@AllArgsConstructor
public class RecordService {
    private final JudgeRecordDao judgeRecordDao;


    public List<LessonJudgeRecordVo> getLessonProblemRecord(Long lessonId, Integer type) {

        return judgeRecordDao.selectLessonRecord(lessonId,type);
    }

    public JudgeRecordSubmitVO getRecord(JudgeRecordSubmitVO vo){
        vo.setUserId(SecurityUser.getUserId());
        return judgeRecordDao.selectRecord(vo);
    }

    public ProblemCompletionVo getRecordAndAnswer(ProblemCompletionVo vo) {
        return judgeRecordDao.selectRecordAndAnswer(vo);
    }

    /**
     * 改判
     * @param vo
     * @return
     */
    public int updateReasonAndStatus(ProblemCompletionVo vo){
        return judgeRecordDao.updateReasonAndStatus(vo);
    }
}
