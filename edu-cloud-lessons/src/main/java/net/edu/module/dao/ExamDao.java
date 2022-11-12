package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ExamEntity;
import net.edu.module.query.ExamQuery;
import net.edu.module.vo.ExamExcelVo;
import net.edu.module.vo.ExamVO;
import net.edu.module.vo.WxExamArrangementVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 考试
*
* @author 小樊 babamu@126.com
* @since 1.0.0 2022-10-09
*/
@Mapper
public interface ExamDao extends BaseDao<ExamEntity> {
    ExamVO selectPaperManage(@Param("paperId") Long paperId);

    IPage<ExamVO> page(Page<ExamVO> page, @Param("query") ExamQuery query);

    IPage<ExamVO> studentPage(Page<ExamVO> page, @Param("query") ExamQuery query) ;

    List<ExamVO> getExamingList(@Param("userId") Long userId);

    List<ExamExcelVo> selectExamProblemInfo(@Param("examId") Long examId,@Param("userId") Long userId);

    List<WxExamArrangementVO> selectExamArrangement(@Param("vo") ExamVO vo);


    List<ExamVO> selectPaperByClassId(List<Long> classIdList);
}