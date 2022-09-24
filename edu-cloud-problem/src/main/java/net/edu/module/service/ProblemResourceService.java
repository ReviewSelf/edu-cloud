package net.edu.module.service;


import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.ProblemResourceVO;
import net.edu.module.query.ProblemResourceQuery;
import net.edu.module.entity.ProblemResourceEntity;

import java.util.List;

/**
 * 问题资源表
 *
 * @author 周建超 
 * @since 1.0.0 2022-09-20
 */
public interface ProblemResourceService extends BaseService<ProblemResourceEntity> {



    List<ProblemResourceVO> getProblemResource(Long id);

    void deleteProblemResource(Long id);

    void saveProblemResource(ProblemResourceVO vo);
}