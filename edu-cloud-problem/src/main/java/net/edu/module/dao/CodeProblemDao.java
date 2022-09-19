package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.CodeProblemEntity;
import net.edu.module.query.CodeProblemQuery;
import net.edu.module.vo.CodeProblemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* 代码题库表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-05
*/
@Mapper
public interface CodeProblemDao extends BaseDao<CodeProblemEntity> {

    int updateStatus(Long problemId);

    IPage<CodeProblemVO> page(Page<CodeProblemVO> page, @Param("query")CodeProblemQuery query);

    int updateSampleNum(Long problemId);

    int updateUsedNum(@Param("id") Long id);

    int updateSubmitTimes(@Param("id") Long id,@Param("isTrue") Boolean isTrue);

    CodeProblemVO selectCodeProblemInfo(@Param("problemId") Long problemId);
}