package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.AbilityDimVO;
import net.edu.module.query.AbilityDimQuery;
import net.edu.module.entity.AbilityDimEntity;

import java.util.List;

/**
 * 能力模块表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-25
 */
public interface AbilityDimService extends BaseService<AbilityDimEntity> {


    void save(AbilityDimVO vo);

    void update(AbilityDimVO vo);

    void delete(List<Long> idList);

    List<AbilityDimVO> getAdList();
}