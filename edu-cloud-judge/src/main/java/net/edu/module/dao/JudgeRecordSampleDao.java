package net.edu.module.dao;


import net.edu.module.vo.JudgeResultVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JudgeRecordSampleDao {
    int insert(JudgeResultVO judgeResultVO);
}
