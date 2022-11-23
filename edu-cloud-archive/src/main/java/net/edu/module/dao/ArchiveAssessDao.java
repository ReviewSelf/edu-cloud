package net.edu.module.dao;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ArchiveAssessEntity;
import net.edu.module.query.ArchiveAssessQuery;
import net.edu.module.vo.ArchiveAssessByCourseIdVo;
import net.edu.module.vo.ArchiveAssessVO;
import net.edu.module.vo.ArchivePointAndTargetVO;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

/**
* 考核点
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-26
*/
@Mapper
public interface ArchiveAssessDao extends BaseDao<ArchiveAssessEntity> {

    IPage<ArchiveAssessVO> selectArchiveAssessByPage(Page<ArchiveAssessVO> page, ArchiveAssessQuery query);

    List<ArchiveAssessVO> selectName();

    Integer insertArchiveAccess1(ArchiveAssessEntity vo);

    ArchiveAssessVO selectArchiveAssessById(Long id);

    void updateArchiveAssess1(ArchiveAssessVO vo);

    void updateArchiveAssess2(ArchiveAssessVO vo);

    List<ArchiveAssessVO> selectAssessByCourseId(Long courseId);

    void insertArchiveAssess1(ArchiveAssessVO vo);

    void updateArchiveAssess3(ArchiveAssessVO vo);

    ArchiveAssessVO selectSummaryStep2(String courseId);

    List<ArchiveAssessByCourseIdVo> selectAssessBycourseId(String courseId);

    void updateByCourseId(Integer courseId, Integer assessId);
    void updateByTargetId(Integer courseId, Integer assessId);

    void insertAssessWeight(ArchiveAssessByCourseIdVo archiveAssessByCourseIdVo);

    List<ArchiveAssessByCourseIdVo> selectAssessByTargetId(String targetId);

    String selectArchiveAssessIdJuge(Integer assessId, Integer targetId);


    void updateAssessWeight(ArchiveAssessByCourseIdVo archiveAssessByCourseIdVo);

    void updateEvaluation(ArchivePointAndTargetVO assess);

    BigDecimal selectWeightSum(ArchiveAssessByCourseIdVo assess);
}
