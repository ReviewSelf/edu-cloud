package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ProblemCodeSampleEntity;
import net.edu.module.vo.CodeProblemVO;
import net.edu.module.vo.FileUploadVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* 测试样例表
*
* @author sqw 
* @since 1.0.0 2022-09-07
*/
@Mapper
public interface ProblemCodeSampleDao extends BaseDao<ProblemCodeSampleEntity> {

    CodeProblemVO selectProblem(@Param("id") Long id);

    void insertSample(@Param("fileUploadVOIn") FileUploadVO fileUploadVOIn,@Param("fileUploadVOOut") FileUploadVO fileUploadVOOut,@Param("id") Long id);
}