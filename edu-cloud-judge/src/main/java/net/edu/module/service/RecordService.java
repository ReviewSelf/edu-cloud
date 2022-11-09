package net.edu.module.service;

import lombok.AllArgsConstructor;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.dao.JudgeRecordDao;
import net.edu.module.vo.ExamJudgeRecordVo;
import net.edu.module.vo.JudgeRecordSubmitVO;
import net.edu.module.vo.lesson.LessonJudgeRecordVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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




    public JudgeRecordSubmitVO getRecord(JudgeRecordSubmitVO vo){
        vo.setUserId(SecurityUser.getUserId());
        return judgeRecordDao.selectRecord(vo);
    }

    public JudgeRecordSubmitVO getRecordAndAnswer(JudgeRecordSubmitVO vo) {
        return judgeRecordDao.selectRecordAndAnswer(vo);
    }

    /**
     * 改判
     * @param vo
     * @return
     */
    public int updateReasonAndStatus(JudgeRecordSubmitVO vo){
        vo.setJudgeUser(SecurityUser.getUserId());
        return judgeRecordDao.updateReasonAndStatus(vo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void statisticsUserJudgeRecord() {
        Long userId = SecurityUser.getUserId();
        //先删除原有统计信息
        judgeRecordDao.deleteStatisticsUserRecord(userId);
        //重新统计信息并插入
        judgeRecordDao.statisticsUserJudgeRecord(userId);
    }
}
