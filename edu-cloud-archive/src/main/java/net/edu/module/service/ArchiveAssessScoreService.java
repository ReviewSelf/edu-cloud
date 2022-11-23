package net.edu.module.service;

import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.ArchiveAssessScoreEntity;
import net.edu.module.vo.ArchiveAssessScoreVO;


import java.util.List;

/**
 * 考试成绩表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-16
 */
public interface ArchiveAssessScoreService extends BaseService<ArchiveAssessScoreEntity> {

    List<ArchiveAssessScoreVO> selectAssessScoreByCourseId(Long courseId);
}
