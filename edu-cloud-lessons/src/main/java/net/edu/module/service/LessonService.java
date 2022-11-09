package net.edu.module.service;


import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.LessonEntity;
import net.edu.module.query.LessonQuery;
import net.edu.module.vo.LessonVO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 课程表
 *
 * @author 马佳浩
 * @since 1.0.0 2022-09-15
 */
public interface LessonService extends BaseService<LessonEntity> {

    PageResult<LessonVO> page(LessonQuery query);

    List<LessonVO> list(LessonQuery query);


    void update(LessonVO vo);

    void updateHomework(LessonVO vo);


    void createLessons(List<LessonVO> voList);

    void sendHomeworkBegin(Long lessonId);

    void sendHomeworkEnd(Long lessonId , String endTime);

    PageResult<LessonVO> homeworkPage(LessonQuery query);

    List<LessonVO> getClassNotStartLesson(Long classId);

    List<LessonVO> getClassAllLesson(Long classId);

    void  closeLessonHomeWork(Long lessonId);

    void homeWorkDeadline();

    void delete(Long classId);

    void updateList(List<LessonVO> list);

    void exportLesson(Long lessonId, HttpServletResponse response) throws IOException;
}
