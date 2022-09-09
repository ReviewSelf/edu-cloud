package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.FillProblemEntity;
import net.edu.module.query.FillProblemQuery;
import net.edu.module.vo.FillProblemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* 填空题表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-05
*/
@Mapper
public interface FillProblemDao extends BaseDao<FillProblemEntity> {

    IPage<FillProblemVO> page(Page<FillProblemVO> page, @Param("query") FillProblemQuery query);

    void updateStatus(Long id);

    int updateUsedNum(@Param("id") Long id);

    int updateSubmitTimes(@Param("id") Long id,@Param("isTrue") Boolean isTrue);
}