package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ArchiveAssessScoreEntity;
import net.edu.module.entity.ArchiveGoalScoreEntity;
import net.edu.module.vo.ArchiveAssessScoreVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 考试成绩表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-16
 */
@Mapper
public interface ArchiveGoalScoreDao extends BaseDao<ArchiveGoalScoreEntity> {




}
