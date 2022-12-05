package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.ArchiveAssessGradesDtVo;
import net.edu.module.vo.ArchiveAssessTestGradesVo;
import net.edu.module.vo.ArchiveCourseSummaryVO;
import net.edu.module.query.ArchiveCourseSummaryQuery;
import net.edu.module.entity.ArchiveCourseSummaryEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * 课程总结
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-14
 */
public interface ArchiveCourseSummaryService extends BaseService<ArchiveCourseSummaryEntity> {

    PageResult<ArchiveCourseSummaryVO> page(ArchiveCourseSummaryQuery query);

    void save(ArchiveCourseSummaryVO vo);

    void update(ArchiveCourseSummaryVO vo);

    void delete(List<Long> idList);

    void exportExcelSummary(Long courseId,Long summaryId, HttpServletResponse response) throws IOException;

    void insertMeasures(ArchiveCourseSummaryVO vo);

    void insertAnalysis(ArchiveCourseSummaryVO vo);

    void insertFinal(ArchiveCourseSummaryVO vo);

    Long creativeSummaryId(ArchiveCourseSummaryVO summaryVO);

    void createTeachingWord( Long courseId,Long summaryId,HttpServletResponse response) throws IOException;

    void getGradesTable(String courseId, String summaryId);

    List<Integer> getScoreEveRage(String courseId, String summaryId);

    List<BigDecimal> selectMannerPq(String courseId);

    List<ArchiveAssessTestGradesVo> selectArchiveStep3(String courseId , String summaryId);

    List<String> selectPeaceData(Integer courseId);

    List<ArchiveAssessGradesDtVo> selectArchiveGradesDt(String courseId, String summaryId);

    void insertProblem(ArchiveCourseSummaryVO vo);

    List<ArchiveCourseSummaryVO> selectAllSummary();
}
