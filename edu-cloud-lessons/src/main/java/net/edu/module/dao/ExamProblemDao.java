package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ExamProblemEntity;
import net.edu.module.query.ExamProblemQuery;
import net.edu.module.vo.ExamProblemVO;
import net.edu.module.vo.ProblemPaperItemEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 课堂练习表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-15
*/
@Mapper
public interface ExamProblemDao extends BaseDao<ExamProblemEntity> {

    int insertProblemList(@Param("list") List<ProblemPaperItemEntity> problemList, Integer paperType, Long lessonId);

    List<ExamProblemVO> selectExamProblem(@Param("query") ExamProblemQuery query);

    int insertProblemListByTeacher(@Param("list") List<ProblemPaperItemEntity> problemList,Long lessonId);
}