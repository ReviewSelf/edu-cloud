package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.AbilityDimEntity;
import net.edu.module.entity.KnowledgePointEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* 能力模块表
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-25
*/
@Mapper
public interface AbilityDimDao extends BaseDao<AbilityDimEntity> {

    List<AbilityDimEntity> getAdList();

    AbilityDimEntity selectBrotherEntity(Long pid);
}