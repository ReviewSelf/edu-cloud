package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.TeachPlanEntity;
import net.edu.module.query.TeachPlanQuery;
import net.edu.module.vo.TeachPlanVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* 教学计划表
*
* @author sqw 
* @since 1.0.0 2022-09-12
*/
@Mapper
public interface TeachPlanDao extends BaseDao<TeachPlanEntity> {

    IPage<TeachPlanVO> page(Page<TeachPlanVO> page, @Param("query") TeachPlanQuery query);

    int updateStatus(@Param("id") Long id);

    int updateUsedNum(@Param("id") Long id);
}