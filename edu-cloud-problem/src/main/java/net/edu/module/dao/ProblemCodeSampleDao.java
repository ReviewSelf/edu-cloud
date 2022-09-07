package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ProblemCodeSampleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 测试样例表
*
* @author sqw 
* @since 1.0.0 2022-09-07
*/
@Mapper
public interface ProblemCodeSampleDao extends BaseDao<ProblemCodeSampleEntity> {

}