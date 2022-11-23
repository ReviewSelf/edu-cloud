package net.edu.module.service.impl;

import lombok.AllArgsConstructor;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.dao.*;
import net.edu.module.entity.ArchiveGoalScoreEntity;
import net.edu.module.service.ArchiveAssessScoreService;
import net.edu.module.service.ArchiveGoalScoreService;
import net.edu.module.vo.ArchiveAssessScoreVO;
import net.edu.module.vo.ArchiveGoalPeopleVO;
import net.edu.module.vo.ArchiveGoalScoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 考试成绩表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-16
 */
@Service
@AllArgsConstructor
public class ArchiveGoalScoreServiceImpl extends BaseServiceImpl<ArchiveGoalScoreDao, ArchiveGoalScoreEntity> implements ArchiveGoalScoreService {

    @Autowired
    private ArchiveGoalScoreDao archiveGoalScoreDao;
    @Autowired
    private ArchiveWeightAssessTestDao archiveWeightAssessTestDao;
    @Autowired
    private ArchiveAssessScoreService archiveAssessScoreService;
    @Override
    public void insertGoalScore(List<ArchiveGoalScoreVO> vo) {
        List<ArchiveGoalScoreEntity> list = new ArrayList<>();
        for (ArchiveGoalScoreVO archiveGoalScoreVO : vo) {
            ArchiveGoalScoreEntity entity = new ArchiveGoalScoreEntity();
            entity.setScore(archiveGoalScoreVO.getScore().toString());
            entity.setCourseId(archiveGoalScoreVO.getCourseId());
            entity.setTotal(archiveGoalScoreVO.getTotal().toString());
            entity.setStuId(archiveGoalScoreVO.getStuId());
            entity.setStuName(archiveGoalScoreVO.getStuName());
            list.add(entity);
        }
        archiveGoalScoreDao.insertGoalScore(list);
    }

    @Override
    public List<ArchiveGoalPeopleVO> getSample(Long courseId) {
        DecimalFormat df = new DecimalFormat("0.00");
        List<ArchiveGoalScoreEntity> entities = archiveGoalScoreDao.selectGoalScore(courseId);
        //考试的数量
        int size = entities.get(0).getScore().substring(1, entities.get(0).getScore().length() - 1).split(",").length;
        System.out.println(size);
        List<ArchiveGoalPeopleVO> list = new ArrayList<>();
        int[] excellent = new int[size];
        int[] good = new int[size];
        int[] medium = new int[size];
        int[] pass = new int[size];
        int[] fail = new int[size];
        for (ArchiveGoalScoreEntity entity : entities) {
            for (int j = 0; j < size; j++) {
                float score = Float.parseFloat(entity.getScore().substring(1, entity.getScore().length() - 1).split(",")[j]);
                if (score >= 90) {
                    excellent[j]++;
                } else if (score >= 80) {
                    good[j]++;
                } else if (score >= 70) {
                    medium[j]++;
                } else if (score >= 60) {
                    pass[j]++;
                } else fail[j]++;
            }
        }
        List<ArchiveAssessScoreVO> vos = archiveAssessScoreService.selectAssessScoreByCourseId(courseId);
        List<Double> weights = archiveWeightAssessTestDao.selectTestByCourseId(courseId);
        Double[] avg = vos.get(0).getAvg();
        for (int i = 0; i < size; i++) {
            ArchiveGoalPeopleVO vo = new ArchiveGoalPeopleVO();
            vo.setTargetName("教学目标"+(i+1));
            vo.setExcellent(excellent[i]);
            vo.setGood(good[i]);
            vo.setMedium(medium[i]);
            vo.setPass(pass[i]);
            vo.setFail(fail[i]);
            vo.setEvaluate(df.format(avg[i]/weights.get(i)/100));
            list.add(vo);
        }


        return list;
    }

    @Override
    public List<ArchiveGoalScoreVO> getUnit(Long courseId) {
        List<ArchiveGoalScoreEntity> entities = archiveGoalScoreDao.selectGoalScore(courseId);
        //考试的数量
        int size = entities.get(0).getScore().substring(1, entities.get(0).getScore().length() - 1).split(",").length;
        List<Double> weights = archiveWeightAssessTestDao.selectTestByCourseId(courseId);
        List<ArchiveGoalScoreVO> list = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            for (int j = 0; j < size; j++) {
                float score = Float.parseFloat(entities.get(i).getScore().substring(1, entities.get(i).getScore().length() - 1).split(",")[j]);
                if(score<weights.get(j)*60){
                    //这个人有一门课不及格
                    ArchiveGoalScoreVO vo = new ArchiveGoalScoreVO();
                    List<Double> s = new ArrayList<>();
                    for (int k = 0; k < size; k++) {
                        s.add(Double.valueOf(entities.get(i).getScore().substring(1, entities.get(i).getScore().length() - 1).split(",")[k]));
                    }

                    vo.setScore(s);
                    vo.setTotal(Double.valueOf(entities.get(i).getTotal()));
                    vo.setStuId(entities.get(i).getStuId());
                    vo.setStuName(entities.get(i).getStuName());
                    list.add(vo);
                    break;
                }
            }
        }
        return list;
    }
}
