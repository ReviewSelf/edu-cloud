package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.AbilityEntity;

import net.edu.module.vo.AbilityVO;

import java.util.List;

/**
 * 能力纬度图
 *
 * @author sqw 
 * @since 1.0.0 2022-10-27
 */
public interface AbilityService extends BaseService<AbilityEntity> {

    List<AbilityEntity> list();

    void save(AbilityVO vo);

    void update(AbilityVO vo);

    void delete(List<Long> idList);
}