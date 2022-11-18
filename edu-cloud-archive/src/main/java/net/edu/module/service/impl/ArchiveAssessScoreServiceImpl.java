package net.edu.module.service.impl;

import lombok.AllArgsConstructor;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.dao.*;
import net.edu.module.entity.ArchiveAssessScoreEntity;
import net.edu.module.entity.ArchiveTestScoreEntity;
import net.edu.module.service.ArchiveAssessScoreService;
import net.edu.module.vo.ArchiveAssessScoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 考试成绩表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-16
 */
@Service
@AllArgsConstructor
public class ArchiveAssessScoreServiceImpl extends BaseServiceImpl<ArchiveAssessScoreDao, ArchiveAssessScoreEntity> implements ArchiveAssessScoreService {

    @Autowired
    private ArchiveTestScoreDao archiveTestScoreDao;
    @Autowired
    private ArchiveWeightAssessTestDao archiveWeightAssessTestDao;


    /**
     * 总评表
     * @param courseId
     * @return
     */
    @Override
    public List<ArchiveAssessScoreVO> selectAssessScoreByCourseId(Long courseId) {
        //所有学生的所有考试的成绩
        List<ArchiveTestScoreEntity> scoreEntities = archiveTestScoreDao.selectTestScoreByCourseId(courseId);
        List<Double> weights = archiveWeightAssessTestDao.selectTestByCourseId(courseId);
        DecimalFormat df = new DecimalFormat("0.0");
        int size = weights.size();
        List<ArchiveAssessScoreVO> list = new ArrayList<>();
        for (int i = 0; i < scoreEntities.size(); i+=size) {
            double sumScore = 0;
            List<String> score = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                Double weight = weights.get(j);
                Double s = Double.valueOf(scoreEntities.get(i + j).getScore());
                s *= weight;
                score.add(df.format(s));
                sumScore+=s;
            }
            ArchiveAssessScoreVO vo = new ArchiveAssessScoreVO();
            vo.setStuId(scoreEntities.get(i).getStuId());
            vo.setStuName(scoreEntities.get(i).getStuName());
            vo.setScore(score);
            vo.setTotal(df.format(sumScore));
            list.add(vo);
        }

        return list;
    }



}
