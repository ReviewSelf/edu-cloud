package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.ArchiveCourseSummaryVO;
import net.edu.module.query.ArchiveCourseSummaryQuery;
import net.edu.module.entity.ArchiveCourseSummaryEntity;

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

    void exportExcelSummary(Long courseId, HttpServletResponse response) throws IOException;

    void insertMeasures(ArchiveCourseSummaryVO vo);

    void insertAnalysis(ArchiveCourseSummaryVO vo);

    void insertFinal(ArchiveCourseSummaryVO vo);
}
