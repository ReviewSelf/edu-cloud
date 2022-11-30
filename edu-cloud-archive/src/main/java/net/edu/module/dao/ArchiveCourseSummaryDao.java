package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ArchiveCourseSummaryEntity;
import net.edu.module.query.ArchiveCourseSummaryQuery;
import net.edu.module.vo.ArchiveAssessTestGradesVo;
import net.edu.module.vo.ArchiveCourseSummaryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* 课程总结
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-14
*/
@Mapper
public interface ArchiveCourseSummaryDao extends BaseDao<ArchiveCourseSummaryEntity> {

    IPage<ArchiveCourseSummaryVO> selectSummaryByPage(Page<ArchiveCourseSummaryVO> page, ArchiveCourseSummaryQuery query);

    void insertMeasures(ArchiveCourseSummaryVO vo);

    void insertAnalysis(ArchiveCourseSummaryVO vo);

    void insertFinal(ArchiveCourseSummaryVO vo);

    Long insertSummaryTable(ArchiveCourseSummaryVO summary);

    Integer selectStudent(String courseId);

    List<String> selectStudentId(String courseId);

    List<ArchiveAssessTestGradesVo> selectStudentIdAndWeight(String courseId);

    List<ArchiveAssessTestGradesVo> selectStudentTestScore(Integer integer);

    Integer selectAssessNum(String courseId);

    List<Integer> selectAssessId(String courseId);

    void insertAssessScore(ArchiveAssessTestGradesVo vo);

    List<String> selectStudentName();

    List<String> selectAssessName(String courseId);

    String selectStudentAssessScore(Integer assessId, String stuId, String summaryId);

    List<Integer> selectAssessScore(String courseId, String summaryId);

    Long selectCourseIdBySummaryId(Long summaryId);
}
