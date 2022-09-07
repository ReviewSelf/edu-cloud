package net.edu.module.dao;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ChoiceProblemEntity;
import net.edu.module.query.ChoiceProblemQuery;
import net.edu.module.vo.ChoiceProblemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* 选择题库表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-05
*/
@Mapper
public interface ChoiceProblemDao extends BaseDao<ChoiceProblemEntity> {


	ChoiceProblemVO selectChoiceProblem(Long id);


	void updateStatus(Integer id);

    IPage<ChoiceProblemVO> page(Page<ChoiceProblemVO> page, @Param("query")ChoiceProblemQuery query);
}