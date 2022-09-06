package net.edu.module.service;


import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.CodeProblemVO;
import net.edu.module.query.CodeProblemQuery;
import net.edu.module.entity.CodeProblemEntity;

import java.util.List;

/**
 * 代码题库表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-05
 */
public interface CodeProblemService extends BaseService<CodeProblemEntity> {

    PageResult<CodeProblemVO> page(CodeProblemQuery query);

    void save(CodeProblemVO vo);

    void update(CodeProblemVO vo);

    void delete(List<Long> idList);

    boolean updateStatus(Integer id);
}