package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ArchiveCourseSummaryConvert;
import net.edu.module.dao.ArchiveAssessDao;
import net.edu.module.entity.ArchiveCourseSummaryEntity;
import net.edu.module.query.ArchiveCourseSummaryQuery;
import net.edu.module.service.ArchiveCourseService;
import net.edu.module.service.ArchiveWeightTargetCourseService;
import net.edu.module.utils.ExcelSummaryUtil;
import net.edu.module.utils.WordUtil;
import net.edu.module.vo.*;
import net.edu.module.vo.ArchiveAssessTestGradesVo;
import net.edu.module.vo.ArchiveCourseSummaryVO;
import net.edu.module.dao.ArchiveCourseSummaryDao;
import net.edu.module.service.ArchiveCourseSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程总结
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-14
 */
@Service
@AllArgsConstructor
public class ArchiveCourseSummaryServiceImpl extends BaseServiceImpl<ArchiveCourseSummaryDao, ArchiveCourseSummaryEntity> implements ArchiveCourseSummaryService {

    @Autowired
    private ArchiveAssessDao archiveAssessDao;

    @Autowired
    private ArchiveWeightTargetCourseService archiveWeightTargetCourseService;

    @Autowired
    private ArchiveCourseSummaryDao archiveCourseSummaryDao;

    @Autowired
    private ArchiveCourseService archiveCourseService;
    @Override
    public PageResult<ArchiveCourseSummaryVO> page(ArchiveCourseSummaryQuery query){

        Page<ArchiveCourseSummaryVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<ArchiveCourseSummaryVO> list =archiveCourseSummaryDao.selectSummaryByPage(page,query);
        return new PageResult<>(list.getRecords(), page.getTotal());

    }

    @Override
    public void save(ArchiveCourseSummaryVO vo) {
        ArchiveCourseSummaryEntity entity = ArchiveCourseSummaryConvert.INSTANCE.convert(vo);
        baseMapper.insert(entity);
    }

    @Override
    public void update(ArchiveCourseSummaryVO vo) {
        ArchiveCourseSummaryEntity entity = ArchiveCourseSummaryConvert.INSTANCE.convert(vo);
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public void insertMeasures(ArchiveCourseSummaryVO vo) {
        archiveCourseSummaryDao.insertMeasures(vo);
    }

    @Override
    public void insertAnalysis(ArchiveCourseSummaryVO vo) {
        archiveCourseSummaryDao.insertAnalysis(vo);
    }

    @Override
    public void insertFinal(ArchiveCourseSummaryVO vo) {
        archiveCourseSummaryDao.insertFinal(vo);
    }

    @Override
    public Long creativeSummaryId(ArchiveCourseSummaryVO summary) {
        archiveCourseSummaryDao.insertSummaryTable(summary);
        Long id = summary.getId();
        return id;
    }

    @Override
    public void getGradesTable(String courseId, String summaryId) {

        List<ArchiveAssessTestGradesVo> idAndWeight = archiveCourseSummaryDao.selectStudentIdAndWeight(courseId);
        System.out.println("评测点ID和权重ByCourseId");
        System.out.println(idAndWeight);
        //第三步，计算考核点成绩
        //3.1获取考核点总数以及考核点id
        Integer assessNum = archiveCourseSummaryDao.selectAssessNum(courseId);
        System.out.println("考核点总数:");
        System.out.println(assessNum);
        //3.2获取考核点id用为判断
        List<Integer> assessArr = archiveCourseSummaryDao.selectAssessId(courseId);
        System.out.println("考核点id：");
        System.out.println(assessArr);
        //通过考核点分类
        for(int i = 0 ; i < assessNum ; i++) {
            List<ArchiveAssessTestGradesVo> studentTestScore = archiveCourseSummaryDao.selectStudentTestScore(assessArr.get(i) , courseId);
//            System.out.println("学生成绩获取：");
//            System.out.println(studentTestScore);
            //通过学生分类
            double sum = 0;
            for(int j = 0 ; j < studentTestScore.size() ; j++) {
                //如果下一条不是同一个同学
                ArchiveAssessTestGradesVo vo = new ArchiveAssessTestGradesVo();
                if(j + 1 < studentTestScore.size()) {
                    if(studentTestScore.get(j).getStuId().equals(studentTestScore.get(j + 1).getStuId())) {
//                        System.out.println("测试");
                    } else {
                        //保存考核点成绩并给sum重置
                        int res = (int) Math.round(sum);
                        vo.setCourseId(courseId);
                        vo.setAssessId(assessArr.get(i));
                        vo.setStuId(studentTestScore.get(j).getStuId());
                        vo.setStuName(studentTestScore.get(j).getStuName());
                        vo.setScore(res);
                        vo.setSummaryId(summaryId);
                        archiveCourseSummaryDao.insertAssessScore(vo);
//                        System.out.println("结果");
//                        System.out.println(res);
                        sum = 0;
                    }
                } else {
                    int res = (int) Math.round(sum);
                    vo.setCourseId(courseId);
                    vo.setAssessId(assessArr.get(i));
                    vo.setStuId(studentTestScore.get(j).getStuId());
                    vo.setStuName(studentTestScore.get(j).getStuName());
                    vo.setScore(res);
                    vo.setSummaryId(summaryId);
                    archiveCourseSummaryDao.insertAssessScore(vo);
//                    System.out.println("结果");
//                    System.out.println(res);
                    sum = 0;
                }
                for(int k = 0 ; k < idAndWeight.size() ; k++) {
                    //判断是哪一个评测点以获取权重
                    if(idAndWeight.get(k).getId() == studentTestScore.get(j).getId()) {
                        sum += idAndWeight.get(k).getWeight() * studentTestScore.get(j).getScore();
                    }
                }
            }
        }
    }

    @Override
    public List<Integer> getScoreEveRage(String courseId, String summaryId) {

        //1.获取学生人数
        Integer studentNum = archiveCourseSummaryDao.selectStudent(courseId);
        //2.获取考核点总数
        Integer assessNum = archiveCourseSummaryDao.selectAssessNum(courseId);
        //3.获取每个考核点的总成绩并计算平均值
        List<Integer> assessScore = archiveCourseSummaryDao.selectAssessScore(courseId , summaryId);
        List<Integer> assessEvage = new ArrayList<>();
        for(int i = 0 ; i < assessScore.size() ; i++) {
            Integer x = assessScore.get(i) / studentNum;
            System.out.println(x);
            assessEvage.add(x);
        }
        System.out.println(assessEvage);
        return assessEvage;
    }

    @Override
    public List<BigDecimal> selectMannerPq(String courseId) {
        List<BigDecimal> list = archiveCourseSummaryDao.selectMannerPq(courseId);
        return list;
    }

    @Override
    public List<ArchiveAssessTestGradesVo> selectArchiveStep3(String courseId, String summaryId) {

        List<ArchiveAssessTestGradesVo> list = archiveCourseSummaryDao.selectStudentIdAndName(courseId);
        System.out.println("学生姓名和id获取：");
        System.out.println(list.get(1).getStudentId());
        for(int i = 0 ; i < list.size() ; i++) {
            list.get(i).setFinalScoreList(archiveCourseSummaryDao.selectFinalScore(list.get(i).getStudentId(), summaryId));
        }
        for(int i = 0 ; i < list.size() ; i++) {
            list.get(i).setPeaceScoreList(archiveCourseSummaryDao.selectPeaceScore(list.get(i).getStudentId() , summaryId));
            BigDecimal score = list.get(i).getPeaceScoreList().get(0).getWeight().multiply(list.get(i).getPeaceScoreList().get(0).getAssessScore());
            list.get(i).setPeaceScore(score.toString());
        }
        return list;
    }

    @Override
    public List<String> selectPeaceData(Integer courseId) {
        Integer TargetNum = archiveCourseSummaryDao.selectTargetByCourseId(courseId);
        Integer AssessNum = archiveCourseSummaryDao.selectPeaceAssessNum(courseId);
        List<String> AssessName = archiveCourseSummaryDao.selectPeaceAssessName(courseId);
        List<BigDecimal> TargetWeightArr = archiveCourseSummaryDao.selectPeaceTargetWeightArr(courseId);
        BigDecimal[][] AssessWeightArr = new BigDecimal[TargetNum][AssessNum];
        BigDecimal[] TargetArr = new BigDecimal[TargetNum];
        BigDecimal m = new BigDecimal(100);
        for(int i = 0 ; i < TargetWeightArr.size() ; i++) {
            TargetArr[i] = TargetWeightArr.get(i).multiply(m);
        }
        List<String> TargetName = archiveCourseSummaryDao.selectTargetName(courseId);
        System.out.println("测试");
        System.out.println(TargetName);
        System.out.println(TargetNum);
        System.out.println(AssessName);
        System.out.println(AssessNum);
        System.out.println(TargetArr);
        System.out.println(TargetWeightArr);

        List<String> list = new ArrayList<>();

        for(int i = 0 ; i < TargetNum ; i++) {
            list.add(TargetName.get(i) + '(' + TargetArr[i] + ')');
        }

        return list;
    }

    @Override
    public List<ArchiveAssessGradesDtVo> selectArchiveGradesDt(String courseId, String summaryId) {
        List<ArchiveAssessGradesDtVo> list = new ArrayList<>();
        Integer AssessNum = archiveCourseSummaryDao.selectAssessNum(courseId);
        List<Integer> AssessId = archiveCourseSummaryDao.selectAssessId(courseId);
        System.out.println("测试：");
        System.out.println(AssessNum);
        System.out.println(AssessId);

        for(int i = 0 ; i < 5 ; i++) {

            ArchiveAssessGradesDtVo x = new ArchiveAssessGradesDtVo();
            switch (i) {
                case 1:
                    x.setName("优秀人数(0.9)");
                    list.add(x);
                    break;
                case 2:
                    x.setName("良好人数(0.8)");
                    list.add(x);
                case 3:
                    x.setName("中等人数(0.7)");
                    list.add(x);
                    break;
                case 4:
                    x.setName("及格人数(0.6)");
                    list.add(x);
                    break;
                case 5:
                    x.setName("不及格(0)");
                    list.add(x);
                    break;
            }
        }
        for(int i = 0 ; i < list.size() ; i++) {
           for(int j = 0 ; j < AssessNum ; j++) {
               if(i == 0) {
                   int n = archiveCourseSummaryDao.selectOutstanding(AssessId.get(j) , summaryId);
               }
           }
        }
        System.out.println(list);
        return null;
    }

    @Override
    public void createTeachingWord(Long courseId, Long summaryId, HttpServletResponse response) {
        System.out.println(courseId+" "+summaryId);
        List<ArchivePlanItemVo> archivePlanItemVoList=archiveCourseService.selectPlanItemByCourseId(courseId);
        System.out.println(archivePlanItemVoList);
        WordUtil.createTeachingCalendarWord(archivePlanItemVoList,response);
    }

    @Override
    public void exportExcelSummary(Long courseId,Long summaryId, HttpServletResponse response) throws IOException {
//        List<ArchivePointAndTargetVO>  archivePointAndTargetVOS=archiveWeightTargetCourseService.selectPointAndTarget(courseId);
//        System.out.println(archivePointAndTargetVOS);
        ExcelSummaryUtil.excelSummaryUtil(courseId,summaryId,response);
    }


}
