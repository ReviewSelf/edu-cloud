package net.edu.module.dao;


import net.edu.module.vo.TeachPlanPaperVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeachPlanPaperDao {
    List<TeachPlanPaperVO> selectTeachPlanPaper(@Param("planId") Long planId);

    void insertTeachPlanPaper(List<TeachPlanPaperVO> planPaperList);

    void deleteTeachPlanPaper(@Param("planId") Long planId);


    List<TeachPlanPaperVO> selectPaperByClassIdList(List<Long> classIdList);
}
