package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.AbilityEntity;
import net.edu.module.vo.AbilityVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* 能力纬度图
*
* @author sqw 
* @since 1.0.0 2022-10-27
*/
@Mapper
public interface AbilityDao extends BaseDao<AbilityEntity> {

    List<AbilityVO> selectAbilityItemList(Long abilityId, Long userId);

}