package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ProblemPaperEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 问题卷表
*
* @author 樊磊 
* @since 1.0.0 2022-09-05
*/
@Mapper
public interface ProblemPaperDao extends BaseDao<ProblemPaperEntity> {
	
}