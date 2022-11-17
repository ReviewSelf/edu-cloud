package net.edu.module.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArchiveTestScoreDao {
    int insertArchiveTestScore(String stuId, String stuName, List list);
}
