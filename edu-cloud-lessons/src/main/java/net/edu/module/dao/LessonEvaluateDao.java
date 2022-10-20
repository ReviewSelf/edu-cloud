package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.LessonEvaluateEntity;
import net.edu.module.vo.LessonEvaluateVO;
import net.edu.module.vo.WxLessonEvaluationVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* 课堂评价
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-18
*/
@Mapper
public interface LessonEvaluateDao extends BaseDao<LessonEvaluateEntity> {

    void save(List<LessonEvaluateVO> list);

    void updateByUserId(LessonEvaluateVO vo);

    List<LessonEvaluateVO> list(Long lessonId);

    List<WxLessonEvaluationVO> selectEvaluate(Long lessonId);
}