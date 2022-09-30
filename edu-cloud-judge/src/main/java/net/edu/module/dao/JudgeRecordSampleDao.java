package net.edu.module.dao;


import net.edu.module.vo.JudgeSampleResultVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JudgeRecordSampleDao {
    int insert(JudgeSampleResultVO judgeSampleResultVO);


}
