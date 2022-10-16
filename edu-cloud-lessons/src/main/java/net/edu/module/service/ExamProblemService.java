package net.edu.module.service;


import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.ExamProblemEntity;
import net.edu.module.query.ExamProblemQuery;
import net.edu.module.vo.ExamPaperVo;
import net.edu.module.vo.ExamProblemVO;
import net.edu.module.vo.ProblemPaperItemEntity;

import java.util.List;

/**
 * 课堂练习表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-15
 */
public interface ExamProblemService extends BaseService<ExamProblemEntity> {

    List<ExamProblemEntity> list(Long examId);



    void copyFromPaper(Long paperId,Long examId);



}