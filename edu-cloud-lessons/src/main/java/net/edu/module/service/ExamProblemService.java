package net.edu.module.service;


import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.ExamProblemEntity;
import net.edu.module.query.ExamProblemQuery;
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

    List<ExamProblemVO> list(ExamProblemQuery query);

    void save(ExamProblemVO vo);

    void update(ExamProblemVO vo);

    void delete(List<Long> idList);

    void updateProblemTime(List<ExamProblemVO> lessonProblemList);

    void insertProblemListByTeacher(List<ProblemPaperItemEntity> list, Long lessonId);
}