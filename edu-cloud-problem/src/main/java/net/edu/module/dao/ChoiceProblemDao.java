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
import org.springframework.security.core.parameters.P;

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


	int updateStatus(Long problemId);

    IPage<ChoiceProblemVO> page(Page<ChoiceProblemVO> page, @Param("query")ChoiceProblemQuery query);


    int deleteOption(@Param("problemId") Long problemId);

    int insertOption(@Param("list") List<ChoiceOptionVO> choiceOptionVO,@Param("problemId")Long problemId);

    int updateUsedNum(@Param("id") Long id);

    int updateSubmitTimes(@Param("id") Long id,@Param("isTrue") Boolean isTrue);

    List<String> selectChoiceOptions(Long problemId, int  flag);
}