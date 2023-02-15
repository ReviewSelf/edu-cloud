package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ClassHoursFlowRecordEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 课时流水表
*
* @author sqw 
* @since 1.0.0 2023-02-15
*/
@Mapper
public interface ClassHoursFlowRecordDao extends BaseDao<ClassHoursFlowRecordEntity> {
	
}