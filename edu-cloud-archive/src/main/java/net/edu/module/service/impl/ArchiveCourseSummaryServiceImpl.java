package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ArchiveCourseSummaryConvert;
import net.edu.module.dao.ArchiveCourseDao;
import net.edu.module.dao.ArchiveExamAttendLogDao;
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
    public ArchiveAssessTestGradesVo getGradesTable(String courseId, String summaryId) {

        ArchiveAssessTestGradesVo tableHead = new ArchiveAssessTestGradesVo();

        //第一步，获取学生学号数组，学生总数
        Integer studentNum = archiveCourseSummaryDao.selectStudent(courseId);
        System.out.println("学生总数：");
        System.out.println(studentNum);
        List<String> studentId = archiveCourseSummaryDao.selectStudentId(courseId);
        System.out.println("学生id：");
        System.out.println(studentId);
        //第二步，获取课程下评测点的id和权重
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
            List<ArchiveAssessTestGradesVo> studentTestScore = archiveCourseSummaryDao.selectStudentTestScore(assessArr.get(i));
            System.out.println("学生成绩获取：");
            System.out.println(studentTestScore);
            //通过学生分类
            double sum = 0;
            for(int j = 0 ; j < studentTestScore.size() ; j++) {
                //如果下一条不是同一个同学
                ArchiveAssessTestGradesVo vo = new ArchiveAssessTestGradesVo();
                if(j + 1 < studentTestScore.size()) {
                    if(studentTestScore.get(j).getStuId().equals(studentTestScore.get(j + 1).getStuId())) {
                        System.out.println("测试");
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
                        System.out.println("结果");
                        System.out.println(res);
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
                    System.out.println("结果");
                    System.out.println(res);
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
        //学生id
        tableHead.setStudentId(studentId);
        System.out.println(tableHead);
        //学生姓名
        tableHead.setName(archiveCourseSummaryDao.selectStudentName());
        //考核点名称
        tableHead.setAssessName(archiveCourseSummaryDao.selectAssessName(courseId));
        //考核点得分
        String[][] scoreArr = new String[assessNum][studentNum];
        for(int i = 0 ; i < assessNum ; i++) {
            System.out.println("1212");
            for(int j = 0 ; j < studentNum ; j++) {
                scoreArr[i][j] = archiveCourseSummaryDao.selectStudentAssessScore(assessArr.get(i) , studentId.get(j) , summaryId);
            }
        }
        tableHead.setAssessScore(scoreArr);
        return tableHead;
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
