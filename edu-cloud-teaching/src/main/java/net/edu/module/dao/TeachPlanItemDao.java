package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.TeachPlanItemEntity;
import net.edu.module.vo.TeachPlanItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 教学日历表
*
* @author sqw
* @since 1.0.0 2022-09-12
*/
@Mapper
public interface TeachPlanItemDao extends BaseDao<TeachPlanItemEntity> {

    List<TeachPlanItemVO> list(@Param("id") Long id);
    TeachPlanItemVO selectPlanItem(@Param("id") Long id);
}
