package net.edu.module.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.edu.framework.common.page.PageResult;
import net.edu.module.dao.ExamRecordDao;
import net.edu.module.query.ExamRecordQuery;
import net.edu.module.vo.exam.ExamScoreVO;
import org.springframework.stereotype.Service;

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
        Page<ExamScoreVO> page = new Page<>(query.getPage(),query.getLimit());
        IPage<ExamScoreVO> list = examRecordDao.selectExamRecordList(page,query);
        return new PageResult<>(list.getRecords(), list.getTotal());
    }
}
