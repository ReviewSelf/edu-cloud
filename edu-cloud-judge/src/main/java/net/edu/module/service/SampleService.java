package net.edu.module.service;

import lombok.AllArgsConstructor;
import net.edu.module.dao.JudgeRecordSampleDao;
import net.edu.module.vo.RecordSampleVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/4 1:45
 * @Version: 1.0
 * @Description:
 */
@Service
@AllArgsConstructor
public class SampleService {

    private final JudgeRecordSampleDao judgeRecordSampleDao;
    public List<RecordSampleVo> getRecordSampleList(Integer problemId){
        return judgeRecordSampleDao.selectRecordSampleList(problemId);
    }
}
