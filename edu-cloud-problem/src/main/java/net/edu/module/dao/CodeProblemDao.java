package net.edu.module.dao;


import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.CodeProblemEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CodeProblemDao extends BaseDao<CodeProblemEntity> {
}
