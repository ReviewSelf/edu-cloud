package net.edu.module.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.edu.framework.common.page.PageResult;
import net.edu.module.dao.ExamRecordDao;
import net.edu.module.query.ExamRecordQuery;
import net.edu.module.vo.exam.ExamProblemRecord;
import net.edu.module.vo.exam.ExamScoreVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

    public ExamScoreVO getUserExamScore(Long examId, Long userId){
        return  examRecordDao.selectUserExamScore(examId,userId);
    }


    public PageResult<ExamScoreVO> getExamRecordList(ExamRecordQuery query) {
        int total=examRecordDao.selectExamRecordListTotal(query);
        if(total>0){
            List<ExamScoreVO> list = examRecordDao.selectExamRecordList(query);
            return new PageResult<>(list, total);
        }
        return new PageResult<>();
    }

    //一键批卷
    @Transactional
    public void makePaper(Long examId, Long userId) {
        ExamScoreVO vo=examRecordDao.selectUserExamScore(examId,userId);
        if(vo!=null && CollUtil.isNotEmpty(vo.getProblemRecords())){
            for (ExamProblemRecord record: vo.getProblemRecords()){
                if(record.getSubmitStatus()!=null){
                    BigDecimal score=BigDecimal.valueOf(0);
                    if(record.getProblemType()==2){
                        score=BigDecimal.valueOf(record.getScore()*record.getPassRate().doubleValue());
                    }else {
                        if(record.getSubmitStatus()==3){
                            score= BigDecimal.valueOf(record.getScore());
                        }
                    }
                    record.setFraction(score);
                    //update
                    if(!score.equals(record.getFraction())){
                        changeProblemScore(score,record.getRecordId());
                    }
                }

            }
        }
    }


    public void changeProblemScore(BigDecimal score,Long id){
        examRecordDao.updateProblemScore(score,id);
    }
}
