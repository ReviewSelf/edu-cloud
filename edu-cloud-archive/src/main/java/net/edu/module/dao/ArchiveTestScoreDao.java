package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ArchiveTestScoreEntity;
import net.edu.module.vo.ArchiveExamAttendLogVO;
import net.edu.module.vo.ArchiveTestNameToGoal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 考试成绩表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-16
 */
@Mapper
public interface ArchiveTestScoreDao extends BaseDao<ArchiveTestScoreEntity> {

    int insertArchiveTestScore(String stuId, String stuName, @Param("list") List<ArchiveTestScoreEntity> testScoreList);

    List<ArchiveTestScoreEntity> selectTestScoreByCourseId(Long courseId);

    List<ArchiveExamAttendLogVO> selectExamAttendLogByExamId(Long aLong);

    List<ArchiveTestNameToGoal> selectTestInfoByIds(String courseId,String stuId);
}
