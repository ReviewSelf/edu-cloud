package net.edu.module.dao;


import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ProblemResourceEntity;
import net.edu.module.vo.ProblemResourceVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 问题资源表
*
* @author 周建超 
* @since 1.0.0 2022-09-20
*/
@Mapper
public interface ProblemResourceDao extends BaseDao<ProblemResourceEntity> {
    List<ProblemResourceVO> selectProblemResource(@Param("id") Long id);

    int deleteProblemResource(@Param("id") Long id);

    int insertProblemResource(ProblemResourceVO vo);
}