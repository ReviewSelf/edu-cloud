package net.edu.module.service;

import net.edu.module.dao.JudgeRecordDao;
import net.edu.module.vo.JudgeRecordSubmitVO;
import net.edu.module.vo.LessonJudgeRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/22 11:03
 * @Version: 1.0
 * @Description:
 */
@Service
public class RecordService {
    @Autowired
    JudgeRecordDao judgeRecordDao;


    public List<LessonJudgeRecordVo> getLessonProblemRecord(Long lessonId, Integer type) {
        System.out.println(lessonId);
        return judgeRecordDao.selectLessonRecord(lessonId,type);
    }

    public JudgeRecordSubmitVO getRecord(JudgeRecordSubmitVO vo){
        return judgeRecordDao.selectRecord(vo);
    }
}
