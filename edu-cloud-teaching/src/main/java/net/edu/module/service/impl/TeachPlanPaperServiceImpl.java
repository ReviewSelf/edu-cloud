package net.edu.module.service.impl;


import lombok.AllArgsConstructor;
import net.edu.module.dao.TeachPlanPaperDao;
import net.edu.module.service.TeachPlanPaperService;
import net.edu.module.vo.TeachPlanPaperVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TeachPlanPaperServiceImpl implements TeachPlanPaperService {
    private final TeachPlanPaperDao teachPlanPaperDao;

    @Override
    public List<TeachPlanPaperVo> getTeachPlanPaper(Long planId) {
        return teachPlanPaperDao.selectTeachPlanPaper(planId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTeachPlanPaper(Long planId) {
        teachPlanPaperDao.deleteTeachPlanPaper(planId);
    }

    @Override
    public void addTeachPlanPaper(List<TeachPlanPaperVo> planPaperList) {
        teachPlanPaperDao.insertTeachPlanPaper(planPaperList);
    }
}
