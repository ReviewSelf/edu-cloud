package net.edu.module.dao;


import net.edu.module.vo.JudgeResultVO;
import net.edu.module.vo.RecordSampleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JudgeRecordSampleDao {
    int insert(JudgeResultVO judgeResultVO);


}
