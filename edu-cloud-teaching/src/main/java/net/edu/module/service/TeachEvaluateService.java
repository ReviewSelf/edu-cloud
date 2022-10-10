package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.TeachEvaluateVO;
import net.edu.module.query.TeachEvaluateQuery;
import net.edu.module.entity.TeachEvaluateEntity;

import java.util.List;

/**
 * 教学评价
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-08
 */
public interface TeachEvaluateService extends BaseService<TeachEvaluateEntity> {

    PageResult<TeachEvaluateVO> page(TeachEvaluateQuery query);

    void save(TeachEvaluateVO vo);

    void update(TeachEvaluateVO vo);

    void delete(List<Long> idList);


}