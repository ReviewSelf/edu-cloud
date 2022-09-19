package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.CodeSampleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 测试样例表
*
* @author sqw 
* @since 1.0.0 2022-09-07
*/
@Mapper
public interface CodeSampleDao extends BaseDao<CodeSampleEntity> {


 //   void insertSample(@Param("fileUploadVOIn") FileUploadVO fileUploadVOIn,@Param("fileUploadVOOut") FileUploadVO fileUploadVOOut,@Param("id") Long id);


}