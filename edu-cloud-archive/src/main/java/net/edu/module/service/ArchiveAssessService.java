package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.ArchiveAssessByCourseIdVo;
import net.edu.module.vo.ArchiveAssessVO;
import net.edu.module.query.ArchiveAssessQuery;
import net.edu.module.entity.ArchiveAssessEntity;
import net.edu.module.vo.ArchivePointAndTargetVO;
import net.edu.module.vo.ArchiveWeightTargetAssessVO;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

/**
 * 考核点
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-26
 */
public interface ArchiveAssessService extends BaseService<ArchiveAssessEntity> {

    PageResult<ArchiveAssessVO> page(ArchiveAssessQuery query);

    ArchiveAssessVO selectArchiveAssessById(Long id);

    void save(ArchiveWeightTargetAssessVO vo);

    void update(ArchiveAssessVO vo);

    void delete(List<Long> idList);

    void assessFromExcel(MultipartFile file);

    ArchiveAssessVO getSummaryStep2(String courseId);

    List<ArchiveAssessByCourseIdVo> getAssessByCourseId(String courseId);

    void deleteByCourseId(String courseId, String assessId);

    void saveAssessWeight(List<ArchiveAssessByCourseIdVo> assess);

    List<ArchiveAssessByCourseIdVo> getAssessByTargetId(String targetId);

    void saveEvaluation(ArchivePointAndTargetVO assess);

    BigDecimal getWeightSum(ArchiveAssessByCourseIdVo assess);

//    Long save(ArchiveAssessExcelVO vo);


    List<ArchiveAssessVO> selectAssessByCourseId(Long courseId);

    void save1(ArchiveAssessVO vo);

    void update1(ArchiveAssessVO vo);

    void deleteAssess(Long courseId, Long targetId,Long assessId);
}
