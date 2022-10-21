package net.edu.module.service;


import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.LessonEvaluateEntity;

import net.edu.module.vo.LessonEvaluateVO;

import java.util.List;

/**
 * 课堂评价
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-18
 */
public interface LessonEvaluateService extends BaseService<LessonEvaluateEntity> {

    List<LessonEvaluateVO> list(Long lessonId);

    void save(List<LessonEvaluateVO> list);

    void update(LessonEvaluateVO vo);

     void sendEvaluate(Long lessonId);

}