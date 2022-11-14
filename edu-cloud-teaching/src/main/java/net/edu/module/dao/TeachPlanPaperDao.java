package net.edu.module.dao;


import net.edu.module.vo.TeachPlanPaperVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeachPlanPaperDao {
    List<TeachPlanPaperVo> selectTeachPlanPaper(@Param("planId") Long planId);

    void insertTeachPlanPaper(List<TeachPlanPaperVo> planPaperList);

    void deleteTeachPlanPaper(@Param("planId") Long planId);


    List<TeachPlanPaperVo> selectPaperByClassIdList(List<Long> classIdList);
}
