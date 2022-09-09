package net.edu.module.service;


import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.TeachPlanEntity;
import net.edu.module.query.TeachPlanQuery;
import net.edu.module.vo.TeachPlanVO;

import java.util.List;

/**
 * 教学计划表
 *
 * @author 阿沐
 * @since 1.0.0 2022-09-08
 */
public interface TeachPlanService extends BaseService<TeachPlanEntity> {

    PageResult<TeachPlanVO> page(TeachPlanQuery query);

    void save(TeachPlanVO vo);

    void update(TeachPlanVO vo);

    void delete(List<Long> idList);
}
