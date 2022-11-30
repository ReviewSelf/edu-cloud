package net.edu.module.service.impl;

import cn.hutool.json.JSONObject;
import lombok.AllArgsConstructor;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.dao.*;
import net.edu.module.entity.ArchiveAssessScoreEntity;
import net.edu.module.entity.ArchiveGoalScoreEntity;
import net.edu.module.entity.ArchiveWeightGoalEntity;
import net.edu.module.service.ArchiveAssessScoreService;
import net.edu.module.service.ArchiveGoalScoreService;
import net.edu.module.vo.*;
import net.edu.module.utils.CalculateProportionUtil;
import net.edu.module.vo.ArchiveGoalScoreInBooKVO;
import net.edu.module.vo.ArchiveSignVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private ArchiveAssessScoreDao archiveAssessScoreDao;
    @Autowired
    private ArchiveWeightGoalDao archiveWeightGoalDao;
    @Autowired
    private ArchiveWeightTargetCourseDao archiveWeightTargetCourseDao;
    @Autowired
    private ArchiveAssessScoreService archiveAssessScoreService;
    @Autowired
    private ArchiveAssessDao archiveAssessDao;
    @Autowired
    private ArchiveSignDao archiveSignDao;
    @Autowired
    private ArchiveGoalScoreDao goalScoreDao;

    //    插入教学目标得分
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

    //    获取样本分析
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
        List<ArchiveGoalScoreVO> vos = selectGoalScoreByCourseId(courseId);

        Double[] avg = vos.get(0).getAvg();
        for (int i = 0; i < size; i++) {
            ArchiveGoalPeopleVO vo = new ArchiveGoalPeopleVO();
            vo.setTargetName("教学目标"+(i+1));
            vo.setExcellent(excellent[i]);
            vo.setGood(good[i]);
            vo.setMedium(medium[i]);
            vo.setPass(pass[i]);
            vo.setFail(fail[i]);
            vo.setEvaluate(df.format(avg[i]/vos.get(0).getWeights().get(i)/100));
            list.add(vo);
        }


        return list;
    }

    //      获取个体分析
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
    @Override
    public ArchiveGoalScoreInBooKVO getGradeInfo(JSONObject classInfo, String id){
        ArchiveGoalScoreInBooKVO archiveGoalScoreInBooKVO =new ArchiveGoalScoreInBooKVO();
        archiveGoalScoreInBooKVO.setMajorName(String.valueOf(classInfo.get("majorName")));
        archiveGoalScoreInBooKVO.setClassName(String.valueOf(classInfo.get("className")));
        List<ArchiveSignVO> archiveSignVO= archiveSignDao.getSignByBookId(id);
        int TargetNum=archiveSignVO.size();
        int actualNUm=0;
        int absentNum=0;
        String courseId= String.valueOf(classInfo.get("courseId"));
        archiveGoalScoreInBooKVO.setTargetNum(String.valueOf(TargetNum));
        List<ArchiveGoalScoreEntity> List= goalScoreDao.selectByStuId(archiveSignVO,courseId);
        actualNUm=List.size();
        absentNum=TargetNum-actualNUm;
        archiveGoalScoreInBooKVO.setAbsentNum(String.valueOf(absentNum));
        archiveGoalScoreInBooKVO.setActualNum(String.valueOf(actualNUm));
        int excellent=0;
        String excellentPercent;
        int  good=0;
        String  goodPercent;
        int  medium=0;
        String  mediunPercent;
        int  pass=0;
        String passPercent;
        int  fail=0;
        String failPercent;
        for (ArchiveGoalScoreEntity item : List){
            if(item.getTotal().compareTo("90")>=0){
                excellent++;
            } else if (item.getTotal().compareTo("80")>=0) {
                good++;
            } else if (item.getTotal().compareTo("70")>=0) {
                medium++;
            } else if (item.getTotal().compareTo("60")>=0) {
                pass++;
            }else {
                fail++;
            }
        }
        excellentPercent=String.valueOf(CalculateProportionUtil.proportionInt(excellent,TargetNum));
        goodPercent =String.valueOf(CalculateProportionUtil.proportionInt(good,TargetNum));
        mediunPercent=String.valueOf(CalculateProportionUtil.proportionInt(medium,TargetNum));
        passPercent=String.valueOf(CalculateProportionUtil.proportionInt(pass,TargetNum));
        failPercent=String.valueOf(CalculateProportionUtil.proportionInt(fail,TargetNum));
        archiveGoalScoreInBooKVO.setExcellent(String.valueOf(excellent));
        archiveGoalScoreInBooKVO.setExcellentPercent(excellentPercent);
        archiveGoalScoreInBooKVO.setGood(String.valueOf(good));
        archiveGoalScoreInBooKVO.setGoodPercent(goodPercent);
        archiveGoalScoreInBooKVO.setMedium(String.valueOf(medium));
        archiveGoalScoreInBooKVO.setMediumPercent(mediunPercent);
        archiveGoalScoreInBooKVO.setPass(String.valueOf(pass));
        archiveGoalScoreInBooKVO.setPassPercent(passPercent);
        archiveGoalScoreInBooKVO.setFail(String.valueOf(fail));
        archiveGoalScoreInBooKVO.setFailPercent(failPercent);
        return archiveGoalScoreInBooKVO;
    }

    @Override
    public List<ArchiveGoalScoreVO> selectGoalScoreByCourseId(Long courseId) {
        //所有学生的所有考试的成绩
        List<ArchiveAssessScoreEntity> scoreEntities = archiveAssessScoreDao.selectAssessScoreByCourseId(courseId);
        DecimalFormat df = new DecimalFormat("0.0");
        List<ArchiveWeightTargetCourseVO> WeightVOS = archiveWeightTargetCourseDao.selectCourseByCourseId(courseId);
        //教学目标的数量
        int TeachSize = WeightVOS.size();
        System.out.println("教学目标的数量" + TeachSize);
        List<ArchiveAssessVO> archiveAssess = archiveAssessDao.selectAssessByCourseId(courseId);
        //考核点的数量
        int assessSize = archiveAssess.size();
        System.out.println("考核点的数量" + assessSize);
        List<ArchiveGoalScoreVO> list = new ArrayList<>();

//        获取所有学生列表
        for (int i = 0; i < scoreEntities.size(); i += assessSize) {
            ArchiveGoalScoreVO vo = new ArchiveGoalScoreVO();
            vo.setStuId(scoreEntities.get(i).getStuId());
            vo.setStuName(scoreEntities.get(i).getStuName());
            list.add(vo);
        }
//          为学生添加成绩
        //对于每个人
        for (ArchiveGoalScoreVO goalScoreVO : list) {
            List<Double> teachScore = new ArrayList<>();//每个人的得分数组
            double total = 0;//每个人的总分
            for (ArchiveWeightTargetCourseVO weightVO : WeightVOS) {
                //所有教学目标
                List<ArchiveWeightGoalEntity> weightGoalEntities = archiveWeightGoalDao.selectGoalByTargetId(weightVO.getId());
                double sum = 0;//每个教学目标得分
                for (ArchiveWeightGoalEntity weightGoalEntity : weightGoalEntities) {
                    //每个教学目标的权重
                    Double weight = weightGoalEntity.getWeight();
                    //每个教学目标的考核点id
                    Long assessId = weightGoalEntity.getAssessId();
                    //根据学生id和考核点id查到成绩
                    String score = archiveAssessScoreDao.selectAssessScore(assessId, goalScoreVO.getStuId());
                    sum += Double.parseDouble(score) * weight;
                }
                total += sum;
                teachScore.add(Double.valueOf(df.format(sum)));
            }
            goalScoreVO.setScore(teachScore);
            goalScoreVO.setTotal(Double.valueOf(df.format(total)));
        }
        List<Double> weights = new ArrayList<>();
        List<ArchiveWeightGoalEntity> archiveWeightGoalEntities = archiveWeightGoalDao.selectGoalByCourseId(courseId);
        double weight = 0;
        for (int i = 0; i < archiveWeightGoalEntities.size(); i++) {
            if(i+1<archiveWeightGoalEntities.size() && Objects.equals(archiveWeightGoalEntities.get(i).getTargetId(), archiveWeightGoalEntities.get(i + 1).getTargetId())){
                weight += archiveWeightGoalEntities.get(i).getWeight();
            }
            else {
                weight += archiveWeightGoalEntities.get(i).getWeight();
                weights.add(Double.valueOf(df.format(weight)));
                weight = 0;
            }
        }
        list.get(0).setWeights(weights);

        //平均分
        Double[] avg = new Double[TeachSize+1];
        for (int i = 0; i < TeachSize+1; i++) {
            avg[i] = 0.0;
        }
        for (ArchiveGoalScoreVO archiveGoalScoreVO : list) {
            for (int j = 0; j < TeachSize; j++) {
                avg[j] += (archiveGoalScoreVO.getScore().get(j));
            }
            avg[TeachSize] += (archiveGoalScoreVO.getTotal());
        }
        for (int i = 0; i < TeachSize+1; i++) {
            avg[i] = Double.valueOf(df.format(avg[i] / list.size()));
        }
        list.get(0).setAvg(avg);
        return list;
    }
}
