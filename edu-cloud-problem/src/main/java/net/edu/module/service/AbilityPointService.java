package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.AbilityEntity;
import net.edu.module.query.AbilityQuery;
import net.edu.module.vo.AbilityVO;

import java.util.List;

public interface AbilityPointService  extends BaseService<AbilityPointService> {
    PageResult<AbilityVO> page(AbilityQuery query);

    void save(AbilityVO vo);

    void update(AbilityVO vo);

    void delete(List<Long> idList);
}
