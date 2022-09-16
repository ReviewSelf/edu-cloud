package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.TeachPlanItemEntity;
import net.edu.module.query.TeachPlanItemQuery;
import net.edu.module.vo.TeachPlanItemVO;

import java.util.List;

/**
 * 教学日历表
 *
 * @author sqw
 * @since 1.0.0 2022-09-12
 */
public interface TeachPlanItemService extends BaseService<TeachPlanItemEntity> {

    List<TeachPlanItemVO> page(Long id);

    void save(TeachPlanItemVO vo);

    void update(TeachPlanItemVO vo);

    void delete(List<Long> idList);
}