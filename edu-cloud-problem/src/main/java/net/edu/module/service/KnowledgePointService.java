package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.KnowledgePointEntity;
import net.edu.module.query.KpProblemQuery;
import net.edu.module.vo.KnowledgePointVO;
import net.edu.module.vo.KpProblemVO;

import java.util.List;

public interface KnowledgePointService extends BaseService<KnowledgePointEntity> {

    void save(KnowledgePointVO vo);

    void update(KnowledgePointVO vo);

    void delete(Long id);
    List<KnowledgePointVO> getKpList();

    Long getSubKpCount(Long pid);

    PageResult<KpProblemVO> getKpProblem(KpProblemQuery query);
}
