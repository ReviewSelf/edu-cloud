package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.TeachEvaluateEntity;
import net.edu.module.query.TeachEvaluateQuery;
import net.edu.module.vo.TeachEvaluateVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* 教学评价
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-08
*/
@Mapper
public interface TeachEvaluateDao extends BaseDao<TeachEvaluateEntity> {
    IPage<TeachEvaluateVO> page(Page<TeachEvaluateVO> page, @Param("query") TeachEvaluateQuery query);

}