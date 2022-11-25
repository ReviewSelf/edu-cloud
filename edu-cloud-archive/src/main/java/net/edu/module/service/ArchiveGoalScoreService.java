package net.edu.module.service;

import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.ArchiveGoalScoreEntity;
import net.edu.module.vo.ArchiveAssessScoreVO;
import net.edu.module.vo.ArchiveGoalPeopleVO;
import net.edu.module.vo.ArchiveGoalScoreVO;

import java.util.List;

/**
 * 考试成绩表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-16
 */
public interface ArchiveGoalScoreService extends BaseService<ArchiveGoalScoreEntity> {


    void insertGoalScore(List<ArchiveGoalScoreVO> vo);

    List<ArchiveGoalPeopleVO> getSample(Long courseId);

    List<ArchiveGoalScoreVO> getUnit(Long courseId);

//    第五步分数
    List<ArchiveGoalScoreVO> selectGoalScoreByCourseId(Long courseId);
}
