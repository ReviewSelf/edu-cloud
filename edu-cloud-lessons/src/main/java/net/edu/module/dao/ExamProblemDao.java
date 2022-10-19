package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ExamProblemEntity;
import net.edu.module.query.ExamAttendLogQuery;
import net.edu.module.query.ExamProblemQuery;
import net.edu.module.vo.ExamAttendLogVO;
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

    IPage<ExamProblemVO> page(Page<ExamProblemVO> page, @Param("query") ExamProblemQuery query);

//    int insertProblemList(@Param("list") List<ProblemPaperItemEntity> problemList, Integer paperType, Long lessonId);
//
//
//    int insertProblemListByTeacher(@Param("list") List<ProblemPaperItemEntity> problemList,Long lessonId);

    int insertExamProblemFromPaper(@Param("problemList") List<ProblemPaperItemEntity> problemList,Long examId);
}