package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.TeachCommunicateRecordEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 沟通记录表
*
* @author sqw 
* @since 1.0.0 2023-02-05
*/
@Mapper
public interface TeachCommunicateRecordDao extends BaseDao<TeachCommunicateRecordEntity> {
	
}