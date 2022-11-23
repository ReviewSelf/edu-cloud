package net.edu.module.dao;

import net.edu.module.entity.ArchiveSignEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArchiveSignDao {

    int insertArchiveSignDao(@Param("list") List<ArchiveSignEntity> list);

}
