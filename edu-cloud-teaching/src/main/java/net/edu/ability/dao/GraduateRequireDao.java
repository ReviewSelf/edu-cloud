package net.edu.ability.dao;


import net.edu.ability.entity.GraduateRequireEntity;
import net.edu.framework.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
* 毕业要求
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-20
*/
@Mapper
public interface GraduateRequireDao extends BaseDao<GraduateRequireEntity> {
	
}