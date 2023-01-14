package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ArchiveCourseSummaryEntity;
import net.edu.module.query.ArchiveCourseSummaryQuery;
import net.edu.module.vo.ArchiveAssessTestGradesVo;
import net.edu.module.vo.ArchiveAssessTestScoreVo;
import net.edu.module.vo.ArchiveCourseSummaryVO;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
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

    List<ArchiveAssessTestGradesVo> selectStudentTestScore(Integer assessId, String courseId);

    Integer selectAssessNum(String courseId);

    List<Integer> selectAssessId(String courseId);

    void insertAssessScore(ArchiveAssessTestGradesVo vo);

    List<String> selectStudentName();

    List<String> selectAssessName(String courseId);

    String selectStudentAssessScore(Integer assessId, String stuId, String summaryId);

    List<Integer> selectAssessScore(String courseId, String summaryId);

    List<BigDecimal> selectMannerPq(Long courseId);

    List<ArchiveAssessTestGradesVo> selectStudentIdAndName(String courseId);

    List<ArchiveAssessTestScoreVo> selectFinalScore(String stuId ,String summaryId);

    List<ArchiveAssessTestScoreVo> selectPeaceScore(String studentId, String summaryId);

    Integer selectPeaceAssessNum(Long courseId);

    List<String> selectPeaceAssessName(Long courseId);

    List<BigDecimal> selectPeaceTargetWeightArr(Long courseId);

    List<Integer> selectTargetId(Long courseId);

    List<Integer> selectPeaceAssessId(Long courseId);

    Integer selectTargetByCourseId(Long courseId);

    List<String> selectTargetName(Long courseId);

    Long selectCourseIdBySummaryId(Long summaryId);

    int selectOutstanding(Integer assessId, String summaryId);

    int selectGood(Integer assessId, String summaryId);

    int selectMid(Integer assessId, String summaryId);

    int selectPass(Integer assessId, String summaryId);

    int selectNotPass(Integer assessId, String summaryId);

    void insertProblem(ArchiveCourseSummaryVO vo);

    List<ArchiveCourseSummaryVO> selectAllSummary();
}
