package net.edu.ability.service;


import net.edu.ability.entity.GraduateRequireEntity;
import net.edu.ability.query.GraduateRequireQuery;
import net.edu.ability.vo.GraduateRequireVO;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;

import java.util.List;

/**
 * 毕业要求
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-20
 */
public interface GraduateRequireService extends BaseService<GraduateRequireEntity> {

    PageResult<GraduateRequireVO> page(GraduateRequireQuery query);

    void save(GraduateRequireVO vo);

    void update(GraduateRequireVO vo);

    void delete(List<Long> idList);
}