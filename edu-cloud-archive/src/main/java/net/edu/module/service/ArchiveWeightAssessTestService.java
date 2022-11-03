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
}
