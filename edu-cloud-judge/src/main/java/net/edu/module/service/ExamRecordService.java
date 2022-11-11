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
import net.edu.module.vo.exam.ExamUserExcelVo;
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


    public List<ExamScoreVO> getExamRecordList(ExamRecordQuery query) {
        return  examRecordDao.selectExamRecordList(query);
    }

    //一键批卷
    @Transactional
    public void makePaper(Long examId, Long userId) {
        ExamScoreVO vo=examRecordDao.selectUserExamScore(examId,userId);
        if(vo!=null && CollUtil.isNotEmpty(vo.getProblemRecords())){
            for (ExamProblemRecord record: vo.getProblemRecords()){
                if(record.getSubmitStatus()!=null){
                    if(!record.getFraction().equals(new BigDecimal(0.00))){
                        Double score=0D;
                        if(record.getSubmitStatus()==3){
                            score= Double.valueOf(record.getScore());
                        }
                        else if(record.getProblemType()==3){
                            if(record.getPassRate()==null){
                                score=0D;
                            }
                            else {
                                score=record.getScore()*record.getPassRate().doubleValue();
                            }
                        }
                        //update
                        changeProblemScore(new BigDecimal(score),record.getRecordId());
                    }
                }

            }
        }
    }


    public void changeProblemScore(BigDecimal score,Long id){
        examRecordDao.updateProblemScore(score,id);
    }


    public List<ExamUserExcelVo> getExamProblemInfoList(Long examId,List<Long> userIdList){
        return examRecordDao.selectExamProblemInfoList(examId,userIdList);
    }
}
