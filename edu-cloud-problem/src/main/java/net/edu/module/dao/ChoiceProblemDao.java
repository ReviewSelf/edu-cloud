package net.edu.module.dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ChoiceProblemEntity;
import net.edu.module.vo.ChoiceProblemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* 选择题库表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-05
*/
@Mapper
public interface ChoiceProblemDao extends BaseDao<ChoiceProblemEntity> {
	String choicePageQuerySql="select pc.*,kp.name as kp_name from problem_choice pc " +
			"left join knowledge_point kp on kp.id =pc.kp_id where deleted=0";
	String wrapperSql="SELECT * from ( " + choicePageQuerySql + " ) AS q ${ew.customSqlSegment}";

	@Select(wrapperSql)
	Page<ChoiceProblemVO> selectChoicePage(Page<ChoiceProblemVO> page, @Param("ew") Wrapper queryWrapper);

	ChoiceProblemVO selectChoiceProblem(Long id);





}