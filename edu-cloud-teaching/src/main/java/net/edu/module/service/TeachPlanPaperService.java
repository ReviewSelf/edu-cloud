package net.edu.module.service;

import net.edu.module.vo.TeachPlanPaperVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeachPlanPaperService {

    List<TeachPlanPaperVO> getTeachPlanPaper(@Param("planId") Long planId);

    void addTeachPlanPaper(List<TeachPlanPaperVO> planPaperList);

    void deleteTeachPlanPaper(@Param("planId") Long planId);


    List<TeachPlanPaperVO> getPaperByClassIdList(List<Long> classIdList);
}
