package net.edu.module.utils;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.MapUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.edu.framework.common.excel.HeadContentCellStyle;
import net.edu.framework.common.utils.ResponseHeadUtils;
import net.edu.module.dao.ArchiveGoalScoreDao;
import net.edu.module.dao.ArchiveWeightTargetCourseDao;
import net.edu.module.entity.ArchiveCourseSummaryEntity;
import net.edu.module.service.ArchiveAssessService;
import net.edu.module.service.ArchiveCourseSummaryService;
import net.edu.module.service.ArchiveGoalScoreService;
import net.edu.module.service.ArchiveWeightTargetCourseService;
import net.edu.module.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@Service
@AllArgsConstructor
public class TemplateUpdateExcelUtil {
    private static List<ArchiveGoalPeopleVO> sample;
    private static TemplateUpdateExcelUtil excelSummaryUtil;
    private static Long courseIdUntil;
    private static Long summaryIdUntil;

    private static List<ArchivePointAndTargetVO> archivePointAndTargetVOS;
    private static List<ArchiveGoalScoreVO> archiveGoalScoreVOS;
    private static ArchiveCourseSummaryEntity summaryEntity;
    private static List<ArchiveWeightTargetCourseVO> archiveWeightTargetCourseVOS;
    @Autowired
    private ArchiveGoalScoreService archiveGoalScoreService;
    @Autowired
    private ArchiveWeightTargetCourseDao archiveWeightTargetCourseDao;
    @Autowired
    private ArchiveCourseSummaryService archiveCourseSummaryService;
    @Autowired
    private ArchiveAssessService archiveAssessService;
    @Autowired
    private ArchiveWeightTargetCourseService archiveWeightTargetCourseService;



    public TemplateUpdateExcelUtil() {

    }

    public static void excelSummaryUtil(Long courseId, Long summaryId, HttpServletResponse response) throws IOException {
        Date date = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy");
        courseIdUntil = courseId;
        summaryIdUntil = summaryId;
        Map<String, Object> map = MapUtils.newHashMap();
        archivePointAndTargetVOS = excelSummaryUtil.archiveWeightTargetCourseService.selectPointAndTarget(courseIdUntil);
        map.put("SysId", archivePointAndTargetVOS.get(0).getSysId());
        map.put("Name", archivePointAndTargetVOS.get(0).getName());
        map.put("Semester", format1.format(date)+'-'+(Integer.valueOf(format1.format(date))+1)+'-'+archivePointAndTargetVOS.get(0).getSemester()); //学期
        map.put("ClassCycle", archivePointAndTargetVOS.get(0).getClassCycle());
        map.put("CourseCategory", archivePointAndTargetVOS.get(0).getCourseCategory());
        map.put("TeachClass", archivePointAndTargetVOS.get(0).getTeachClass());
        map.put("Teacher", archivePointAndTargetVOS.get(0).getTeacher());
        map.put("Credit", archivePointAndTargetVOS.get(0).getCredit());
        map.put("Place", archivePointAndTargetVOS.get(0).getPlace());
        map.put("Assessment", archivePointAndTargetVOS.get(0).getAssessment());

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        map.put("Date", format.format(date));
        List<BigDecimal> bigDecimals = excelSummaryUtil.archiveCourseSummaryService.selectMannerPq((courseIdUntil));
        map.put("usual", bigDecimals.get(0));
        map.put("final", bigDecimals.get(1));

        archiveGoalScoreVOS = excelSummaryUtil.archiveGoalScoreService.selectGoalScoreByCourseId(summaryIdUntil, courseIdUntil);
        summaryEntity = excelSummaryUtil.archiveCourseSummaryService.getById(summaryIdUntil);
        archiveWeightTargetCourseVOS = excelSummaryUtil.archiveWeightTargetCourseDao.selectCourseByCourseId(courseIdUntil);
        sample = excelSummaryUtil.archiveGoalScoreService.getSample(summaryIdUntil);
        String name = "课程实施总结表.xlsx";
        ResponseHeadUtils.responseEXCELHead(response, name);

        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                .withTemplate("E:\\template\\summaryTemplate.xlsx")
                .excelType(ExcelTypeEnum.XLSX)
                .build();

        excelSummaryUtil.test(excelWriter, map);

        excelWriter.finish();
        response.flushBuffer();
    }

    public static void summary1(ExcelWriter excelWriter, Map<String, Object> map) {

        WriteSheet writeSheet = EasyExcel.writerSheet("指标点与教学目标").build();
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        excelWriter.fill(map, writeSheet);
        excelWriter.fill(archivePointAndTargetVOS, fillConfig, writeSheet);

    }

    public static void summary2(ExcelWriter excelWriter, Map<String, Object> map) {

        ArchiveAssessByCourseIdVo assess = new ArchiveAssessByCourseIdVo();
        assess.setCourseId(Math.toIntExact(excelSummaryUtil.courseIdUntil));
        ArchiveAssessTableVo archiveAssessTableVo = excelSummaryUtil.archiveAssessService.getWeightTable(assess);


        List<String> peaceString = excelSummaryUtil.archiveCourseSummaryService.selectPeaceData(courseIdUntil);
        List<FillData2> list1 = new ArrayList<>();
        for (String s : peaceString) {
            FillData2 fillData2 = new FillData2();
            fillData2.setPeace(s.substring(6, s.length() - 1));
            list1.add(fillData2);
        }
        while(list1.size()<6){
            FillData2 fillData2 = new FillData2();
            fillData2.setPeace(String.valueOf(0));
            list1.add(fillData2);
        }
        String[][] rouCome = archiveAssessTableVo.getRouCome();
        int size = rouCome[0].length;
        List<Fill2> list2 = new ArrayList<>();
        

        for (int i = 0; i < rouCome.length; i++) {
            Fill2 fill2 = new Fill2();
            fill2.setName(rouCome[i][0]);
            if (size==2){
                fill2.setAssess1((rouCome[i][1]));
            }
            else if (size==3){
                fill2.setAssess1(rouCome[i][1]);
                fill2.setAssess2(rouCome[i][2]);

            }
            else if (size==4){
                fill2.setAssess1(rouCome[i][1]);
                fill2.setAssess2(rouCome[i][2]);
                fill2.setAssess3(rouCome[i][3]);

            }
            else if (size==5){
                fill2.setAssess1(rouCome[i][1]);
                fill2.setAssess2(rouCome[i][2]);
                fill2.setAssess3(rouCome[i][3]);
                fill2.setAssess4(rouCome[i][4]);
            }
            else {
                fill2.setAssess1(rouCome[i][1]);
                fill2.setAssess2(rouCome[i][2]);
                fill2.setAssess3(rouCome[i][3]);
                fill2.setAssess4(rouCome[i][4]);
                fill2.setAssess5(rouCome[i][5]);
            }
            list2.add(fill2);
        }
        WriteSheet writeSheet = EasyExcel.writerSheet("考核比例设置").build();
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        excelWriter.fill(map, writeSheet);
        excelWriter.fill(list1, writeSheet);
        excelWriter.fill(list2, writeSheet);
    }

    public static void summary3(ExcelWriter excelWriter, Map<String, Object> map) {
        String course = courseIdUntil.toString(), summary = summaryIdUntil.toString();
        List<ArchiveAssessTestGradesVo> archiveAssessTestGradesVos = excelSummaryUtil.archiveCourseSummaryService.selectArchiveStep3(course, summary);
        int size = archiveAssessTestGradesVos.get(0).getFinalScoreList().size();
        for (ArchiveAssessTestGradesVo archiveAssessTestGradesVo : archiveAssessTestGradesVos) {
            if (size == 0) {
                archiveAssessTestGradesVo.setFirst(0.0);
                archiveAssessTestGradesVo.setSecond(0.0);
                archiveAssessTestGradesVo.setThird(0.0);
                archiveAssessTestGradesVo.setFourth(0.0);
                archiveAssessTestGradesVo.setFifth(0.0);
                map.put("wei1", 0);
                map.put("wei2", 0);
                map.put("wei3", 0);
                map.put("wei4", 0);
                map.put("wei5", 0);
            } else if (size == 1) {
                archiveAssessTestGradesVo.setFirst(archiveAssessTestGradesVo.getFinalScoreList().get(0).getAssessScore().doubleValue());
                archiveAssessTestGradesVo.setSecond(0.0);
                archiveAssessTestGradesVo.setThird(0.0);
                archiveAssessTestGradesVo.setFourth(0.0);
                archiveAssessTestGradesVo.setFifth(0.0);
                map.put("wei1", archiveAssessTestGradesVo.getFinalScoreList().get(0).getWeight().doubleValue() * 100);
                map.put("wei2", 0);
                map.put("wei3", 0);
                map.put("wei4", 0);
                map.put("wei5", 0);
            } else if (size == 2) {
                archiveAssessTestGradesVo.setFirst(archiveAssessTestGradesVo.getFinalScoreList().get(0).getAssessScore().doubleValue());
                archiveAssessTestGradesVo.setSecond(archiveAssessTestGradesVo.getFinalScoreList().get(1).getAssessScore().doubleValue());
                archiveAssessTestGradesVo.setThird(0.0);
                archiveAssessTestGradesVo.setFourth(0.0);
                archiveAssessTestGradesVo.setFifth(0.0);
                map.put("wei1", archiveAssessTestGradesVo.getFinalScoreList().get(0).getWeight().doubleValue() * 100);
                map.put("wei2", archiveAssessTestGradesVo.getFinalScoreList().get(1).getWeight().doubleValue() * 100);
                map.put("wei3", 0);
                map.put("wei4", 0);
                map.put("wei5", 0);
            } else if (size == 3) {
                archiveAssessTestGradesVo.setFirst(archiveAssessTestGradesVo.getFinalScoreList().get(0).getAssessScore().doubleValue());
                archiveAssessTestGradesVo.setSecond(archiveAssessTestGradesVo.getFinalScoreList().get(1).getAssessScore().doubleValue());
                archiveAssessTestGradesVo.setThird(archiveAssessTestGradesVo.getFinalScoreList().get(2).getAssessScore().doubleValue());
                archiveAssessTestGradesVo.setFourth(0.0);
                archiveAssessTestGradesVo.setFifth(0.0);
                map.put("wei1", archiveAssessTestGradesVo.getFinalScoreList().get(0).getWeight().doubleValue() * 100);
                map.put("wei2", archiveAssessTestGradesVo.getFinalScoreList().get(1).getWeight().doubleValue() * 100);
                map.put("wei3", archiveAssessTestGradesVo.getFinalScoreList().get(2).getWeight().doubleValue() * 100);
                map.put("wei4", 0);
                map.put("wei5", 0);
            } else if (size == 4) {
                archiveAssessTestGradesVo.setFirst(archiveAssessTestGradesVo.getFinalScoreList().get(0).getAssessScore().doubleValue());
                archiveAssessTestGradesVo.setSecond(archiveAssessTestGradesVo.getFinalScoreList().get(1).getAssessScore().doubleValue());
                archiveAssessTestGradesVo.setThird(archiveAssessTestGradesVo.getFinalScoreList().get(2).getAssessScore().doubleValue());
                archiveAssessTestGradesVo.setFourth(archiveAssessTestGradesVo.getFinalScoreList().get(3).getAssessScore().doubleValue());
                archiveAssessTestGradesVo.setFifth(0.0);
                map.put("wei1", archiveAssessTestGradesVo.getFinalScoreList().get(0).getWeight().doubleValue() * 100);
                map.put("wei2", archiveAssessTestGradesVo.getFinalScoreList().get(1).getWeight().doubleValue() * 100);
                map.put("wei3", archiveAssessTestGradesVo.getFinalScoreList().get(2).getWeight().doubleValue() * 100);
                map.put("wei4", archiveAssessTestGradesVo.getFinalScoreList().get(3).getWeight().doubleValue() * 100);
                map.put("wei5", 0);
            } else {
                archiveAssessTestGradesVo.setFirst(archiveAssessTestGradesVo.getFinalScoreList().get(0).getAssessScore().doubleValue());
                archiveAssessTestGradesVo.setSecond(archiveAssessTestGradesVo.getFinalScoreList().get(1).getAssessScore().doubleValue());
                archiveAssessTestGradesVo.setThird(archiveAssessTestGradesVo.getFinalScoreList().get(2).getAssessScore().doubleValue());
                archiveAssessTestGradesVo.setFourth(archiveAssessTestGradesVo.getFinalScoreList().get(3).getAssessScore().doubleValue());
                archiveAssessTestGradesVo.setFifth(archiveAssessTestGradesVo.getFinalScoreList().get(4).getAssessScore().doubleValue());
                map.put("wei1", archiveAssessTestGradesVo.getFinalScoreList().get(0).getWeight().doubleValue() * 100);
                map.put("wei2", archiveAssessTestGradesVo.getFinalScoreList().get(1).getWeight().doubleValue() * 100);
                map.put("wei3", archiveAssessTestGradesVo.getFinalScoreList().get(2).getWeight().doubleValue() * 100);
                map.put("wei4", archiveAssessTestGradesVo.getFinalScoreList().get(3).getWeight().doubleValue() * 100);
                map.put("wei5", archiveAssessTestGradesVo.getFinalScoreList().get(4).getWeight().doubleValue() * 100);
            }
            archiveAssessTestGradesVo.setPeace(Double.valueOf(archiveAssessTestGradesVo.getPeaceScore()));
        }
        while (archiveAssessTestGradesVos.size()<=200){
            ArchiveAssessTestGradesVo archiveAssessTestGradesVo = new ArchiveAssessTestGradesVo();
            archiveAssessTestGradesVos.add(archiveAssessTestGradesVo);
        }
        WriteSheet writeSheet = EasyExcel.writerSheet("成绩录入表").build();
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        excelWriter.fill(archiveAssessTestGradesVos, fillConfig, writeSheet);
        excelWriter.fill(map, writeSheet);
    }

    public static void summary4(ExcelWriter excelWriter, Map<String, Object> map) {
        String course = courseIdUntil.toString(), summary = summaryIdUntil.toString();
        List<Integer> scoreEveRage = excelSummaryUtil.archiveCourseSummaryService.getScoreEveRage(course, summary);
        //成绩分布情况
        List<ArchiveAssessGradesDtVo> archiveAssessGradesDtVos = excelSummaryUtil.archiveCourseSummaryService.selectArchiveGradesDt(course, summary);
        ArchiveAssessTableVo weightTableStep4 = excelSummaryUtil.archiveAssessService.getWeightTableStep4(courseIdUntil);
        String[][] rouCome = weightTableStep4.getRouCome(); //考核点对教学目标支撑比例填充
        List<FillData4> weight = new ArrayList<>();
        int length = rouCome[0].length;
        for (String[] strings : rouCome) {
            if (length == 1) {
                FillData4 fillData4 = new FillData4();
                fillData4.setTeachName(strings[0]);
                fillData4.setAssessWeight1("0");
                fillData4.setAssessWeight2("0");
                fillData4.setAssessWeight3("0");
                fillData4.setAssessWeight4("0");
                fillData4.setAssessWeight5("0");
                weight.add(fillData4);
            } else if (length == 2) {
                FillData4 fillData4 = new FillData4();
                fillData4.setTeachName(strings[0]);
                fillData4.setAssessWeight1(strings[1]);
                fillData4.setAssessWeight2("0");
                fillData4.setAssessWeight3("0");
                fillData4.setAssessWeight4("0");
                fillData4.setAssessWeight5("0");
                weight.add(fillData4);
            } else if (length == 3) {
                FillData4 fillData4 = new FillData4();
                fillData4.setTeachName(strings[0]);
                fillData4.setAssessWeight1(strings[1]);
                fillData4.setAssessWeight2(strings[2]);
                fillData4.setAssessWeight3("0");
                fillData4.setAssessWeight4("0");
                fillData4.setAssessWeight5("0");
                weight.add(fillData4);
            } else if (length == 4) {
                FillData4 fillData4 = new FillData4();
                fillData4.setTeachName(strings[0]);
                fillData4.setAssessWeight1(strings[1]);
                fillData4.setAssessWeight2(strings[2]);
                fillData4.setAssessWeight3(strings[3]);
                fillData4.setAssessWeight4("0");
                fillData4.setAssessWeight5("0");
                weight.add(fillData4);
            } else if (length == 5) {
                FillData4 fillData4 = new FillData4();
                fillData4.setTeachName(strings[0]);
                fillData4.setAssessWeight1(strings[1]);
                fillData4.setAssessWeight2(strings[2]);
                fillData4.setAssessWeight3(strings[3]);
                fillData4.setAssessWeight4(strings[4]);
                fillData4.setAssessWeight5("0");
                weight.add(fillData4);
            } else {
                FillData4 fillData4 = new FillData4();
                fillData4.setTeachName(strings[0]);
                fillData4.setAssessWeight1(strings[1]);
                fillData4.setAssessWeight2(strings[2]);
                fillData4.setAssessWeight3(strings[3]);
                fillData4.setAssessWeight4(strings[4]);
                fillData4.setAssessWeight5(strings[5]);
                weight.add(fillData4);
            }
        }

        map.put("ProblemAnalysis", summaryEntity.getProblemAnalysis());
        //平均分填充
        List<Fill4> avg = new ArrayList<>();
        for (Integer integer : scoreEveRage) {
            Fill4 fill4 = new Fill4();
            fill4.setAvg(integer.toString());
            avg.add(fill4);
        }
        while(avg.size()<5){
            Fill4 fill4 = new Fill4();
            fill4.setAvg("0");
            avg.add(fill4);
        }
        //成绩分布情况填充
        int size = archiveAssessGradesDtVos.get(0).getAppraise().size();
        for (ArchiveAssessGradesDtVo archiveAssessGradesDtVo : archiveAssessGradesDtVos) {
            if (size == 0) {
                archiveAssessGradesDtVo.setAssess1("0");
                archiveAssessGradesDtVo.setAssess2("0");
                archiveAssessGradesDtVo.setAssess3("0");
                archiveAssessGradesDtVo.setAssess4("0");
                archiveAssessGradesDtVo.setAssess5("0");
            } else if (size == 1) {
                archiveAssessGradesDtVo.setAssess1(String.valueOf(archiveAssessGradesDtVo.getAppraise().get(0)));
                archiveAssessGradesDtVo.setAssess2("0");
                archiveAssessGradesDtVo.setAssess3("0");
                archiveAssessGradesDtVo.setAssess4("0");
                archiveAssessGradesDtVo.setAssess5("0");
            } else if (size == 2) {
                archiveAssessGradesDtVo.setAssess1(String.valueOf(archiveAssessGradesDtVo.getAppraise().get(0)));
                archiveAssessGradesDtVo.setAssess2(String.valueOf(archiveAssessGradesDtVo.getAppraise().get(1)));
                archiveAssessGradesDtVo.setAssess3("0");
                archiveAssessGradesDtVo.setAssess4("0");
                archiveAssessGradesDtVo.setAssess5("0");
            } else if (size == 3) {
                archiveAssessGradesDtVo.setAssess1(String.valueOf(archiveAssessGradesDtVo.getAppraise().get(0)));
                archiveAssessGradesDtVo.setAssess2(String.valueOf(archiveAssessGradesDtVo.getAppraise().get(1)));
                archiveAssessGradesDtVo.setAssess3(String.valueOf(archiveAssessGradesDtVo.getAppraise().get(2)));
                archiveAssessGradesDtVo.setAssess4("0");
                archiveAssessGradesDtVo.setAssess5("0");
            } else if (size == 4) {
                archiveAssessGradesDtVo.setAssess1(String.valueOf(archiveAssessGradesDtVo.getAppraise().get(0)));
                archiveAssessGradesDtVo.setAssess2(String.valueOf(archiveAssessGradesDtVo.getAppraise().get(1)));
                archiveAssessGradesDtVo.setAssess3(String.valueOf(archiveAssessGradesDtVo.getAppraise().get(2)));
                archiveAssessGradesDtVo.setAssess4(String.valueOf(archiveAssessGradesDtVo.getAppraise().get(3)));
                archiveAssessGradesDtVo.setAssess5("0");
            } else {
                archiveAssessGradesDtVo.setAssess1(String.valueOf(archiveAssessGradesDtVo.getAppraise().get(0)));
                archiveAssessGradesDtVo.setAssess2(String.valueOf(archiveAssessGradesDtVo.getAppraise().get(1)));
                archiveAssessGradesDtVo.setAssess3(String.valueOf(archiveAssessGradesDtVo.getAppraise().get(2)));
                archiveAssessGradesDtVo.setAssess4(String.valueOf(archiveAssessGradesDtVo.getAppraise().get(3)));
                archiveAssessGradesDtVo.setAssess5(String.valueOf(archiveAssessGradesDtVo.getAppraise().get(4)));
            }
        }
        //写入表头
        WriteSheet writeSheet = EasyExcel.writerSheet("考核得分情况分析").build();
        excelWriter.fill(map, writeSheet);
        FillConfig fillConfig1 = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();
        FillConfig fillConfig2 = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        excelWriter.fill(avg, fillConfig1, writeSheet);
        excelWriter.fill(archiveAssessGradesDtVos, fillConfig2, writeSheet);
        excelWriter.fill(weight, fillConfig2, writeSheet);
    }

    public static void summary5(ExcelWriter excelWriter, Map<String, Object> map) {
        int size = archiveGoalScoreVOS.get(0).getScore().size();
        List<Fill5> weight = new ArrayList<>();

        for (int i = 0; i < archiveGoalScoreVOS.get(0).getWeights().size(); i++) {
            Fill5 fill5 = new Fill5();
            fill5.setWeight(archiveGoalScoreVOS.get(0).getWeights().get(i)*100);
            weight.add(fill5);
        }
        while(weight.size()<6){
            Fill5 fill5 = new Fill5();
            fill5.setWeight(0.0);
            weight.add(fill5);
        }

        for (ArchiveGoalScoreVO archiveGoalScoreVO : archiveGoalScoreVOS) {
            if (size == 0) {
                archiveGoalScoreVO.setTeachScore1(0.0);
                archiveGoalScoreVO.setTeachScore2(0.0);
                archiveGoalScoreVO.setTeachScore3(0.0);
                archiveGoalScoreVO.setTeachScore4(0.0);
                archiveGoalScoreVO.setTeachScore5(0.0);
                archiveGoalScoreVO.setTeachScore6(0.0);
            } else if (size == 1) {
                archiveGoalScoreVO.setTeachScore1(archiveGoalScoreVO.getScore().get(0));
                archiveGoalScoreVO.setTeachScore2(0.0);
                archiveGoalScoreVO.setTeachScore3(0.0);
                archiveGoalScoreVO.setTeachScore4(0.0);
                archiveGoalScoreVO.setTeachScore5(0.0);
                archiveGoalScoreVO.setTeachScore6(0.0);
            } else if (size == 2) {
                archiveGoalScoreVO.setTeachScore1(archiveGoalScoreVO.getScore().get(0));
                archiveGoalScoreVO.setTeachScore2(archiveGoalScoreVO.getScore().get(1));
                archiveGoalScoreVO.setTeachScore3(0.0);
                archiveGoalScoreVO.setTeachScore4(0.0);
                archiveGoalScoreVO.setTeachScore5(0.0);
                archiveGoalScoreVO.setTeachScore6(0.0);
            } else if (size == 3) {
                archiveGoalScoreVO.setTeachScore1(archiveGoalScoreVO.getScore().get(0));
                archiveGoalScoreVO.setTeachScore2(archiveGoalScoreVO.getScore().get(1));
                archiveGoalScoreVO.setTeachScore3(archiveGoalScoreVO.getScore().get(2));
                archiveGoalScoreVO.setTeachScore4(0.0);
                archiveGoalScoreVO.setTeachScore5(0.0);
                archiveGoalScoreVO.setTeachScore6(0.0);
            } else if (size == 4) {
                archiveGoalScoreVO.setTeachScore1(archiveGoalScoreVO.getScore().get(0));
                archiveGoalScoreVO.setTeachScore2(archiveGoalScoreVO.getScore().get(1));
                archiveGoalScoreVO.setTeachScore3(archiveGoalScoreVO.getScore().get(2));
                archiveGoalScoreVO.setTeachScore4(archiveGoalScoreVO.getScore().get(3));
                archiveGoalScoreVO.setTeachScore5(0.0);
                archiveGoalScoreVO.setTeachScore6(0.0);
            } else if (size == 5) {
                archiveGoalScoreVO.setTeachScore1(archiveGoalScoreVO.getScore().get(0));
                archiveGoalScoreVO.setTeachScore2(archiveGoalScoreVO.getScore().get(1));
                archiveGoalScoreVO.setTeachScore3(archiveGoalScoreVO.getScore().get(2));
                archiveGoalScoreVO.setTeachScore4(archiveGoalScoreVO.getScore().get(3));
                archiveGoalScoreVO.setTeachScore5(archiveGoalScoreVO.getScore().get(4));
                archiveGoalScoreVO.setTeachScore6(0.0);
            } else if (size == 6) {
                archiveGoalScoreVO.setTeachScore1(archiveGoalScoreVO.getScore().get(0));
                archiveGoalScoreVO.setTeachScore2(archiveGoalScoreVO.getScore().get(1));
                archiveGoalScoreVO.setTeachScore3(archiveGoalScoreVO.getScore().get(2));
                archiveGoalScoreVO.setTeachScore4(archiveGoalScoreVO.getScore().get(3));
                archiveGoalScoreVO.setTeachScore5(archiveGoalScoreVO.getScore().get(4));
                archiveGoalScoreVO.setTeachScore6(archiveGoalScoreVO.getScore().get(5));
            }
        }
        while(archiveGoalScoreVOS.size()<=200){
            ArchiveGoalScoreVO archiveGoalScoreVO = new ArchiveGoalScoreVO();
            archiveGoalScoreVOS.add(archiveGoalScoreVO);
        }
        WriteSheet writeSheet = EasyExcel.writerSheet("总评达成").build();
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        FillConfig fillConfig1 = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();
        excelWriter.fill(map, writeSheet);
        excelWriter.fill(archiveGoalScoreVOS, fillConfig, writeSheet);
        excelWriter.fill(weight, fillConfig1, writeSheet);
    }

    public static void summary6(ExcelWriter excelWriter, Map<String, Object> map) {
        int sum = 0;//计算总人数
        DecimalFormat df = new DecimalFormat("0");
        sum+= sample.get(0).getExcellent();
        sum+= sample.get(0).getMedium();
        sum+= sample.get(0).getGood();
        sum+= sample.get(0).getFail();
        sum+= sample.get(0).getPass();
        map.put("Sum",sum);
        map.put("Improvement", summaryEntity.getImprovement());

        //计算总体
        int Excellent=0,Good=0,Medium=0,Pass=0,Fail=0;
        for (ArchiveGoalScoreVO archiveGoalScoreVO:archiveGoalScoreVOS) {
            if(archiveGoalScoreVO.getStuId()!=null){
                if (archiveGoalScoreVO.getTotal() >= 90) Excellent++;
                else if (archiveGoalScoreVO.getTotal() >= 80) Good++;
                else if (archiveGoalScoreVO.getTotal() >= 70) Medium++;
                else if (archiveGoalScoreVO.getTotal() >= 60) Pass++;
                else Fail++;
            }
        }
        map.put("Excellent",Excellent);
        map.put("ExcellentPercent",df.format(Excellent*100.0/sum));
        map.put("Good",Good);
        map.put("GoodPercent",df.format(Good*100.0/sum));
        map.put("Medium",Medium);
        map.put("MediumPercent",df.format(Medium*100.0/sum));
        map.put("Pass",Pass);
        map.put("PassPercent",df.format(Pass*100.0/sum));
        map.put("Fail",Fail);
        map.put("FailPercent",df.format(Fail*100.0/sum));
        int i;
        for (i = 0; i < sample.size(); i++) {
            map.put("Excellent"+(i+1),sample.get(i).getExcellent());
            map.put("ExcellentPercent"+(i+1),sample.get(i).getExcellent()*100/sum);
            map.put("Good"+(i+1),sample.get(i).getGood());
            map.put("GoodPercent"+(i+1),sample.get(i).getGood()*100/sum);
            map.put("Medium"+(i+1),sample.get(i).getMedium());
            map.put("MediumPercent"+(i+1),sample.get(i).getMedium()*100/sum);
            map.put("Pass"+(i+1),sample.get(i).getPass());
            map.put("PassPercent"+(i+1),sample.get(i).getPass()*100/sum);
            map.put("Fail"+(i+1),sample.get(i).getFail());
            map.put("FailPercent"+(i+1),sample.get(i).getFail()*100/sum);
            map.put("Evaluate"+(i+1),Double.valueOf(sample.get(i).getEvaluate()));
        }
        while(i<6){
            map.put("Excellent"+(i+1),0);
            map.put("ExcellentPercent"+(i+1),0);
            map.put("Good"+(i+1),0);
            map.put("GoodPercent"+(i+1),0);
            map.put("Medium"+(i+1),0);
            map.put("MediumPercent"+(i+1),0);
            map.put("Pass"+(i+1),0);
            map.put("PassPercent"+(i+1),0);
            map.put("Fail"+(i+1),0);
            map.put("FailPercent"+(i+1),0);
            map.put("Evaluate"+(i+1),0.00);
            i++;
        }
        //写入表头
        WriteSheet writeSheet = EasyExcel.writerSheet("考核分析表（样本）").build();
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        excelWriter.fill(map, writeSheet);

    }

    public static void summary7(ExcelWriter excelWriter, Map<String, Object> map) {
        List<ArchiveGoalScoreVO> unit = excelSummaryUtil.archiveGoalScoreService.getUnit(summaryIdUntil);
        int size = archiveGoalScoreVOS.get(0).getScore().size();
        for (ArchiveGoalScoreVO archiveGoalScoreVO : unit) {
            if (size == 0) {
                archiveGoalScoreVO.setTeachScore1(0.0);
                archiveGoalScoreVO.setTeachScore2(0.0);
                archiveGoalScoreVO.setTeachScore3(0.0);
                archiveGoalScoreVO.setTeachScore4(0.0);
                archiveGoalScoreVO.setTeachScore5(0.0);
                archiveGoalScoreVO.setTeachScore6(0.0);
            } else if (size == 1) {
                archiveGoalScoreVO.setTeachScore1(archiveGoalScoreVO.getScore().get(0));
                archiveGoalScoreVO.setTeachScore2(0.0);
                archiveGoalScoreVO.setTeachScore3(0.0);
                archiveGoalScoreVO.setTeachScore4(0.0);
                archiveGoalScoreVO.setTeachScore5(0.0);
                archiveGoalScoreVO.setTeachScore6(0.0);
            } else if (size == 2) {
                archiveGoalScoreVO.setTeachScore1(archiveGoalScoreVO.getScore().get(0));
                archiveGoalScoreVO.setTeachScore2(archiveGoalScoreVO.getScore().get(1));
                archiveGoalScoreVO.setTeachScore3(0.0);
                archiveGoalScoreVO.setTeachScore4(0.0);
                archiveGoalScoreVO.setTeachScore5(0.0);
                archiveGoalScoreVO.setTeachScore6(0.0);
            } else if (size == 3) {
                archiveGoalScoreVO.setTeachScore1(archiveGoalScoreVO.getScore().get(0));
                archiveGoalScoreVO.setTeachScore2(archiveGoalScoreVO.getScore().get(1));
                archiveGoalScoreVO.setTeachScore3(archiveGoalScoreVO.getScore().get(2));
                archiveGoalScoreVO.setTeachScore4(0.0);
                archiveGoalScoreVO.setTeachScore5(0.0);
                archiveGoalScoreVO.setTeachScore6(0.0);
            } else if (size == 4) {
                archiveGoalScoreVO.setTeachScore1(archiveGoalScoreVO.getScore().get(0));
                archiveGoalScoreVO.setTeachScore2(archiveGoalScoreVO.getScore().get(1));
                archiveGoalScoreVO.setTeachScore3(archiveGoalScoreVO.getScore().get(2));
                archiveGoalScoreVO.setTeachScore4(archiveGoalScoreVO.getScore().get(3));
                archiveGoalScoreVO.setTeachScore5(0.0);
                archiveGoalScoreVO.setTeachScore6(0.0);
            } else if (size == 5) {
                archiveGoalScoreVO.setTeachScore1(archiveGoalScoreVO.getScore().get(0));
                archiveGoalScoreVO.setTeachScore2(archiveGoalScoreVO.getScore().get(1));
                archiveGoalScoreVO.setTeachScore3(archiveGoalScoreVO.getScore().get(2));
                archiveGoalScoreVO.setTeachScore4(archiveGoalScoreVO.getScore().get(3));
                archiveGoalScoreVO.setTeachScore5(archiveGoalScoreVO.getScore().get(4));
                archiveGoalScoreVO.setTeachScore6(0.0);
            } else if (size == 6) {
                archiveGoalScoreVO.setTeachScore1(archiveGoalScoreVO.getScore().get(0));
                archiveGoalScoreVO.setTeachScore2(archiveGoalScoreVO.getScore().get(1));
                archiveGoalScoreVO.setTeachScore3(archiveGoalScoreVO.getScore().get(2));
                archiveGoalScoreVO.setTeachScore4(archiveGoalScoreVO.getScore().get(3));
                archiveGoalScoreVO.setTeachScore5(archiveGoalScoreVO.getScore().get(4));
                archiveGoalScoreVO.setTeachScore6(archiveGoalScoreVO.getScore().get(5));
            }
        }
        map.put("AnalysisDescription", summaryEntity.getAnalysisDescription());

        //写入表头
        WriteSheet writeSheet = EasyExcel.writerSheet("考核分析表（个体）").build();
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        excelWriter.fill(map, writeSheet);
        excelWriter.fill(unit, fillConfig, writeSheet);
    }

    public static void summary8(ExcelWriter excelWriter, Map<String, Object> map) {

        for (int i = 0; i < archivePointAndTargetVOS.size(); i++) {
            map.put("Graduate"+(i+1),archivePointAndTargetVOS.get(i).getGraduateRequire());
            map.put("Teach"+(i+1),archivePointAndTargetVOS.get(i).getTeachTarget());
            map.put("Result"+(i+1),Double.valueOf(sample.get(i).getEvaluate()));
            map.put("Approach"+(i+1),archivePointAndTargetVOS.get(i).getApproach());
            map.put("Basis"+(i+1),archivePointAndTargetVOS.get(i).getEvaluationBasis());
            map.put("Method"+(i+1),archivePointAndTargetVOS.get(i).getEvaluationMethod());
        }
        map.put("Problem", summaryEntity.getProblem());
        map.put("Measures", summaryEntity.getMeasures());
        map.put("Resources", summaryEntity.getResources());

        //写入表头
        WriteSheet writeSheet = EasyExcel.writerSheet("课程实施总结表").build();
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        excelWriter.fill(map, writeSheet);

    }



    @PostConstruct
    public void init() {
        excelSummaryUtil = this;
    }

    @Async
    public void test(ExcelWriter excelWriter, Map<String, Object> map) {
        summary1(excelWriter, map);
        summary2(excelWriter, map);
        summary3(excelWriter, map);
        summary4(excelWriter, map);
        summary5(excelWriter, map);
        summary6(excelWriter, map);
        summary7(excelWriter, map);
        summary8(excelWriter, map);

    }


    @Data
    public static class FillData2 {
        private String peace;

    }

    @Data
    public static class Fill2{
        private String name;
        private String assess1;
        private String assess2;
        private String assess3;
        private String assess4;
        private String assess5;
    }

    @Data
    public static class Fill4 {
        private String avg;
    }

    @Data
    public static class FillData4 {
        private String teachName;
        private String assessWeight1;
        private String assessWeight2;
        private String assessWeight3;
        private String assessWeight4;
        private String assessWeight5;
    }
    @Data
    public static class Fill5 {
        private Double weight;
    }
}


