package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ArchiveSignEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArchiveSignDao extends BaseDao<ArchiveSignEntity> {

    int insertArchiveSignDao(@Param("list") List<ArchiveSignEntity> list);

}
