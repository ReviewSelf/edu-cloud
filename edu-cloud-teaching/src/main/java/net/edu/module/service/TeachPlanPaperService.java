package net.edu.module.service;

import net.edu.module.vo.TeachPlanPaperVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeachPlanPaperService {

    List<TeachPlanPaperVo> getTeachPlanPaper(@Param("planId") Long planId);

    void addTeachPlanPaper(List<TeachPlanPaperVo> planPaperList);

    void deleteTeachPlanPaper(@Param("planId") Long planId);


    List<TeachPlanPaperVo> getPaperByClassIdList(List<Long> classIdList);
}
