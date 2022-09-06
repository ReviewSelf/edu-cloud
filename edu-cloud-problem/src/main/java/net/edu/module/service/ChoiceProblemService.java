package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.ChoiceProblemVO;
import net.edu.module.query.ChoiceProblemQuery;
import net.edu.module.entity.ChoiceProblemEntity;

import java.util.List;

/**
 * 选择题库表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-05
 */
public interface ChoiceProblemService extends BaseService<ChoiceProblemEntity> {

    PageResult<ChoiceProblemVO> page(ChoiceProblemQuery query);

    void save(ChoiceProblemVO vo);

    void update(ChoiceProblemVO vo);

    void delete(List<Long> idList);

    boolean updateStatus(Integer id);
}