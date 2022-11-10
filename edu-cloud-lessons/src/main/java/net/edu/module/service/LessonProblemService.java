package net.edu.module.service;



import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.LessonProblemVO;
import net.edu.module.query.LessonProblemQuery;
import net.edu.module.entity.LessonProblemEntity;
import net.edu.module.vo.ProblemPaperItemEntity;

import java.util.List;

/**
 * 课堂练习表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-15
 */
public interface LessonProblemService extends BaseService<LessonProblemEntity> {

    List<LessonProblemVO> list(LessonProblemQuery query);

    void save(LessonProblemVO vo);

    void update(LessonProblemVO vo);

    void delete(List<Long> idList);

    void copyFromPlanItem(Long planItemId, Long lessonId);

    void updateProblemTime(List<LessonProblemVO> lessonProblemList);

    void insertProblemListByTeacher(List<ProblemPaperItemEntity> list, Long lessonId);

    void updateHomeworkUsed(List<LessonProblemVO> list);
}