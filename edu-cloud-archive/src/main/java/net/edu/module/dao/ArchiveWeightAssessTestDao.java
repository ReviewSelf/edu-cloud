package net.edu.module.dao;

import net.edu.module.entity.ArchiveWeightAssessTestEntity;
import net.edu.module.vo.ArchiveWeightAssessTestVO;
import net.edu.module.vo.ArchiveWeightTargetAssessVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author weng
 * @date 2022/11/3 - 11:32
 **/
@Mapper
public interface ArchiveWeightAssessTestDao {

    List<ArchiveWeightAssessTestVO> selectTestName(Long assessId);

    Integer insertTestWeight(List<ArchiveWeightAssessTestVO> VOS);

    void updateDeleted(Long testId);

    List<ArchiveWeightAssessTestVO> selectArchiveTestAll(Long assessId);

    int insertAssessTest(ArchiveWeightAssessTestEntity archiveWeightAssessTestEntity);

    List<Double> selectTestByCourseId(Long courseId);
}
