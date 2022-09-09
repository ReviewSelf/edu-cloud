package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.TeachPlanEntity;
import net.edu.module.query.TeachPlanQuery;
import net.edu.module.vo.TeachPlanVO;
import org.apache.ibatis.annotations.Mapper;

/**
* 教学计划表
*
* @author 阿沐
* @since 1.0.0 2022-09-08
*/
@Mapper
public interface TeachPlanDao extends BaseDao<TeachPlanEntity> {
    IPage<TeachPlanVO> getTeachPlanByPage(Page<TeachPlanVO> page, TeachPlanQuery query);
}
