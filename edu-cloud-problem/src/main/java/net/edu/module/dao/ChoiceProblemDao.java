package net.edu.module.dao;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ChoiceProblemEntity;
import net.edu.module.query.ChoiceProblemQuery;
import net.edu.module.vo.ChoiceOptionVO;
import net.edu.module.vo.ChoiceProblemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 选择题库表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-05
*/
@Mapper
public interface ChoiceProblemDao extends BaseDao<ChoiceProblemEntity> {

    

    ChoiceProblemVO selectChoiceProblem(Long problemId);


	void updateStatus(Long problemId);

    IPage<ChoiceProblemVO> page(Page<ChoiceProblemVO> page, @Param("query")ChoiceProblemQuery query);

    List<ChoiceOptionVO> selectOption(@Param("problemId") Long problemId);

    void deleteOption(@Param("id") Long problemId);

    void insertOption(@Param("list") List<ChoiceOptionVO> choiceOptionVO,@Param("problemId")Long problemId);

    void updateOptionNum(@Param("id") Long problemId);
}