package net.edu.module.service;

import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.ArchiveWeightAssessTestEntity;
import net.edu.module.vo.ArchiveWeightAssessTestVO;

import java.util.List;


/**
 * @author weng
 * @date 2022/11/3 - 11:39
 **/
public interface ArchiveWeightAssessTestService extends BaseService<ArchiveWeightAssessTestEntity> {

    Integer insertTestWeight(List<ArchiveWeightAssessTestVO> VOS);

    List<ArchiveWeightAssessTestVO> selectAssessTest(Long assessId);

    void delete(Long testId);

    List<ArchiveWeightAssessTestVO> selectArchiveTestAll(Long assessId);

    List<ArchiveWeightAssessTestVO> selectAssessWeight(ArchiveWeightAssessTestVO archiveWeightAssessTestVO);

    void deleteTestWeight(Integer id);

    void saveTestWeight(List<ArchiveWeightAssessTestVO> archiveWeightAssessTestVO);

    List<ArchiveWeightAssessTestVO> selectTestInfo(String courseId, String assessId);
}
