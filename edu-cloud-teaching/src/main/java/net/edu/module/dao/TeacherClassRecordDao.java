package net.edu.module.dao;

import net.edu.module.vo.ClassUserRecordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface TeacherClassRecordDao {
    void insertClassUserRecord(@Param("vo") ClassUserRecordVO vo);
}
