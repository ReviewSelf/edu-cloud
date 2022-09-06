package net.edu.module.service;

import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.KnowledgePointEntity;
import net.edu.module.vo.KnowledgePointVO;

import java.util.List;

public interface KnowledgePointService extends BaseService<KnowledgePointEntity> {

    void save(KnowledgePointVO vo);

    void update(KnowledgePointVO vo);

    void delete(Long id);
    List<KnowledgePointVO> getKpList();

    Long getSubKpCount(Long pid);
}
