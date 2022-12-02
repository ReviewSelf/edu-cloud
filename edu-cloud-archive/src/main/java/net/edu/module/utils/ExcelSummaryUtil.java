package net.edu.module.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import lombok.AllArgsConstructor;
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
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
@Service
@AllArgsConstructor
public class ExcelSummaryUtil {
    private static List<ArchiveGoalPeopleVO> sample;
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

    private static ExcelSummaryUtil excelSummaryUtil;

    private static Long courseIdUntil;

    private static Long summaryIdUntil;

    private static List<ArchivePointAndTargetVO> archivePointAndTargetVOS;

    private static List<ArchiveGoalScoreVO> archiveGoalScoreVOS;

    private static ArchiveCourseSummaryEntity summaryEntity;

    private static List<ArchiveWeightTargetCourseVO> archiveWeightTargetCourseVOS;
    @Autowired
    private ArchiveGoalScoreDao archiveGoalScoreDao;

    public ExcelSummaryUtil() {

    }


    @PostConstruct
    public void init() {
        excelSummaryUtil = this;
        excelSummaryUtil.archiveWeightTargetCourseDao = this.archiveWeightTargetCourseDao;
        excelSummaryUtil.archiveWeightTargetCourseService = this.archiveWeightTargetCourseService;
        excelSummaryUtil.archiveGoalScoreService = this.archiveGoalScoreService;
        excelSummaryUtil.archiveAssessService = this.archiveAssessService;
    }

    public static void excelSummaryUtil(Long courseId,Long summaryId,HttpServletResponse response) throws IOException {

        courseIdUntil=courseId;
        summaryIdUntil=summaryId;

        archivePointAndTargetVOS= excelSummaryUtil.archiveWeightTargetCourseService.selectPointAndTarget(courseIdUntil);
        archiveGoalScoreVOS = excelSummaryUtil.archiveGoalScoreService.selectGoalScoreByCourseId(summaryIdUntil,courseIdUntil);
        summaryEntity = excelSummaryUtil.archiveCourseSummaryService.getById(summaryIdUntil);
        archiveWeightTargetCourseVOS = excelSummaryUtil.archiveWeightTargetCourseDao.selectCourseByCourseId(courseIdUntil);
        sample = excelSummaryUtil.archiveGoalScoreService.getSample(summaryIdUntil);
        String name = "课程实施总结表.xlsx";
        ResponseHeadUtils.responseEXCELHead(response,name);

        //内容样式
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);//细实线
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        //设计内容居中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);      //设置内容自动换行
        contentWriteCellStyle.setWrapped(true);
        //设置头部样式
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //设置头部标题居中
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                .registerWriteHandler(new SimpleColumnWidthStyleStrategy(19))
//                .registerWriteHandler(new CellRowHeightStyleStrategy())
                .registerWriteHandler(horizontalCellStyleStrategy)
                .build();
        //生成工作簿

        ExcelSummaryUtil excelSummaryUtil=new ExcelSummaryUtil();
        excelSummaryUtil.test(excelWriter);
//        summary1(excelWriter);
//        summary2(excelWriter);
//        summary5(excelWriter);
//        summary6(excelWriter);
//        summary7(excelWriter);
//        summary8(excelWriter);
        excelWriter.finish();
        response.flushBuffer();
    }

    @Async
    public void test(ExcelWriter excelWriter){
        summary1(excelWriter);
        summary2(excelWriter);
        summary3(excelWriter);
        summary5(excelWriter);
        summary6(excelWriter);
        summary7(excelWriter);
        summary8(excelWriter);
    }

    public static void summary1(ExcelWriter excelWriter){
        List<List<String>> dataList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        //设置表头
        List<ArchivePointAndTargetVO> archivePointAndTargetVOS= excelSummaryUtil.archiveWeightTargetCourseService.selectPointAndTarget(courseIdUntil);
        //写入表头
        WriteSheet writeSheet = EasyExcel.writerSheet("指标点与教学目标")
                .registerWriteHandler(new CellRowHeightStyleStrategy())
                .registerWriteHandler(new SimpleColumnWidthStyleStrategy(25))
                .registerWriteHandler(HeadContentCellStyle.myHorizontalCellStyleStrategy())
                .build();
        //设置内容

        //第一行内容
        System.out.println(archivePointAndTargetVOS);
        list.add("教学编号");
        list.add(String.valueOf(archivePointAndTargetVOS.get(0).getSysId()));
        list.add("课程名称");
        list.add(String.valueOf(archivePointAndTargetVOS.get(0).getName()));
        list.add("学期");
        list.add(String.valueOf(archivePointAndTargetVOS.get(0).getSemester()));
        list.add("上课时间");
        list.add(String.valueOf(archivePointAndTargetVOS.get(0).getClassCycle()));
        list.add("课程性质");
        list.add(String.valueOf(archivePointAndTargetVOS.get(0).getCourseCategory()));
        dataList.add((list));
        excelWriter.write(dataList, writeSheet);
        list= new ArrayList<>();
        dataList= new ArrayList<>();


        //第二行内容
        list.add("教学班");
        list.add(String.valueOf(archivePointAndTargetVOS.get(0).getTeachClass()));
        list.add("任课老师");
        list.add(String.valueOf(archivePointAndTargetVOS.get(0).getTeacher()));
        list.add("学分");
        list.add(String.valueOf(archivePointAndTargetVOS.get(0).getCredit()));
        list.add("上课地点");
        list.add(String.valueOf(archivePointAndTargetVOS.get(0).getPlace()));
        list.add("考核方式");
        list.add(String.valueOf(archivePointAndTargetVOS.get(0).getAssessment()));
        dataList.add((list));
        excelWriter.write(dataList, writeSheet);
        list= new ArrayList<>();
        dataList= new ArrayList<>();

        //第三行内容
        list.add("毕业要求");
        list.add("考核指标点");
        list.add("指标点描述");
        list.add("对应教学目标");
        list.add("教学目标");
        list.add("达成途径");
        list.add("评价依据");
        list.add("评价方式");
        dataList.add((list));
        excelWriter.write(dataList, writeSheet);
        list= new ArrayList<>();
        dataList= new ArrayList<>();

        //下面几行
        for (int i = 0; i < archivePointAndTargetVOS.size(); i++) {
            System.out.println(archivePointAndTargetVOS.get(i));
            //写入内容
            list.add(String.valueOf(archivePointAndTargetVOS.get(i).getGraduateRequire()));
            list.add(String.valueOf(archivePointAndTargetVOS.get(i).getPointId()));
            list.add(String.valueOf(archivePointAndTargetVOS.get(i).getTargetDescription()));
            list.add("教学目标"+(i+1));
            list.add(String.valueOf(archivePointAndTargetVOS.get(i).getTeachTarget()));
            list.add(String.valueOf(archivePointAndTargetVOS.get(i).getApproach()));
            list.add(String.valueOf(archivePointAndTargetVOS.get(i).getEvaluationBasis()));
            list.add(String.valueOf(archivePointAndTargetVOS.get(i).getEvaluationMethod()));
            dataList.add((list));
            excelWriter.write(dataList, writeSheet);
            list= new ArrayList<>();
            dataList= new ArrayList<>();
        }
        excelWriter.write(dataList,writeSheet);

    }
    public static void summary2(ExcelWriter excelWriter){
        List<List<String>> dataList = new ArrayList<>();
        List<String> list;
        ArchiveAssessByCourseIdVo assess = new ArchiveAssessByCourseIdVo();
        assess.setCourseId(Math.toIntExact(excelSummaryUtil.courseIdUntil));
        List<ArchivePointAndTargetVO> archivePointAndTargetVOS= excelSummaryUtil.archiveWeightTargetCourseService.selectPointAndTarget(courseIdUntil);
        ArchiveAssessTableVo archiveAssessTableVo = excelSummaryUtil.archiveAssessService.getWeightTable(assess);
        System.out.println(archiveAssessTableVo);
        //设置表头
        //写入表头
        WriteSheet writeSheet = EasyExcel.writerSheet("考试比例设置")
                .registerWriteHandler(new SimpleColumnWidthStyleStrategy(25))
                .registerWriteHandler(HeadContentCellStyle.myHorizontalCellStyleStrategy())
                .build();
        //设置内容

        //第一行内容
        list= new ArrayList<>();
        list.add("教学编号");
        list.add(String.valueOf(archivePointAndTargetVOS.get(0).getSysId()));
        list.add("课程名称");
        list.add(String.valueOf(archivePointAndTargetVOS.get(0).getName()));
        list.add("学期");
        list.add(String.valueOf(archivePointAndTargetVOS.get(0).getSemester()));
        list.add("上课时间");
        list.add(String.valueOf(archivePointAndTargetVOS.get(0).getClassCycle()));
        list.add("课程性质");
        list.add(String.valueOf(archivePointAndTargetVOS.get(0).getCourseCategory()));
        dataList.add((list));


        //第二行内容
        list= new ArrayList<>();
        list.add("教学班");
        list.add(String.valueOf(archivePointAndTargetVOS.get(0).getTeachClass()));
        list.add("任课老师");
        list.add(String.valueOf(archivePointAndTargetVOS.get(0).getTeacher()));
        list.add("学分");
        list.add(String.valueOf(archivePointAndTargetVOS.get(0).getCredit()));
        list.add("上课地点");
        list.add(String.valueOf(archivePointAndTargetVOS.get(0).getPlace()));
        list.add("考核方式");
        list.add(String.valueOf(archivePointAndTargetVOS.get(0).getAssessment()));
        dataList.add((list));

        //第三行空
        list= new ArrayList<>();
        list.add("");
        dataList.add(list);

        //第四行
        list= new ArrayList<>();
        list.add("考核点占比");
        dataList.add(list);

        //考核点和教学目标占比
        String RouCome[][]=archiveAssessTableVo.getRouCome();
        System.out.println(RouCome.length);
        for(int i =0;i<RouCome.length;i++){
            list= new ArrayList<>();
            System.out.println(RouCome[i].length);
            for(int j=0;j<RouCome[i].length;j++){
                list.add(RouCome[i][j]);
            }
            dataList.add(list);
            System.out.println(dataList);
        }
        excelWriter.write(dataList,writeSheet);
    }

    public static void summary3(ExcelWriter excelWriter){
        String course = courseIdUntil.toString(),summary = summaryIdUntil.toString();
        List<ArchiveAssessTestGradesVo> archiveAssessTestGradesVos = excelSummaryUtil.archiveCourseSummaryService.selectArchiveStep3(course, summary);

        List<List<String>> headList = new ArrayList<>();
        List<String> head = new ArrayList<>();
        head.add("学号");
        head.add("学号");
        head.add("学号");
        headList.add(head);
        head = new ArrayList<>();

        head.add("姓名");
        head.add("姓名");
        head.add("姓名");
        headList.add(head);
        head = new ArrayList<>();

        for (int i = 0; i < archiveAssessTestGradesVos.get(0).getFinalScoreList().size(); i++) {
            head.add("期末考核");
            head.add("考核点"+(i+1));
            head.add(String.valueOf(archiveAssessTestGradesVos.get(0).getFinalScoreList().get(i).getWeight()));
            headList.add(head);
            head = new ArrayList<>();
        }

        head.add("平时考核");
        head.add("平时考核");
        head.add("100");
        headList.add(head);

        List<List<String>> dataList = new ArrayList<>();
        List<String> list = new ArrayList<>();

        for (int i = 0; i < archiveAssessTestGradesVos.size(); i++) {
            list.add(archiveAssessTestGradesVos.get(i).getStudentId());
            list.add(archiveAssessTestGradesVos.get(i).getStudentName());
            for (int j = 0; j < archiveAssessTestGradesVos.get(i).getFinalScoreList().size(); j++) {
                list.add(archiveAssessTestGradesVos.get(i).getFinalScoreList().get(j).getAssessScore().toString());
            }
            list.add(archiveAssessTestGradesVos.get(i).getPeaceScore());
            dataList.add(list);
            list = new ArrayList<>();
        }

        WriteSheet writeSheet = EasyExcel.writerSheet("成绩录入表")
                .head(headList)
                .registerWriteHandler(new CustomMergeStrategy(headList, 0, 0, true, false))
                .registerWriteHandler(HeadContentCellStyle.myHorizontalCellStyleStrategy())
                .build();

        excelWriter.write(dataList, writeSheet);
    }

    public static void summary4(ExcelWriter excelWriter){

    }

    public static void summary5(ExcelWriter excelWriter){
        List<String> list = new ArrayList<>();
        //设置表头

        List<List<String>> dataList = new ArrayList<>();
        List<List<String>> headList = new ArrayList<>();
        List<String> head = new ArrayList<>();
        head.add("总评达成评价值");
        head.add("课程名称");
        headList.add(head);

        head = new ArrayList<>();
        head.add("总评达成评价值");
        head.add(String.valueOf(archivePointAndTargetVOS.get(0).getName()));
        headList.add(head);

        head = new ArrayList<>();
        head.add("总评达成评价值");
        head.add("教学编号");
        headList.add(head);

        head = new ArrayList<>();
        head.add("总评达成评价值");
        head.add(String.valueOf(archivePointAndTargetVOS.get(0).getSysId()));
        headList.add(head);

        head = new ArrayList<>();
        head.add("总评达成评价值");
        head.add("学期");
        headList.add(head);

        head = new ArrayList<>();
        head.add("总评达成评价值");
        head.add(archivePointAndTargetVOS.get(0).getGrade()+'-'+(Integer.parseInt(archivePointAndTargetVOS.get(0).getGrade())+1)+'-'+archivePointAndTargetVOS.get(0).getSemester());
        headList.add(head);

        head = new ArrayList<>();
        head.add("总评达成评价值");
        head.add("学分");
        headList.add(head);

        head = new ArrayList<>();
        head.add("总评达成评价值");
        head.add(archivePointAndTargetVOS.get(0).getCredit());
        headList.add(head);

        //设置内容


        //第三行
        list.add("题目类型");
        list.add("题目类型");
        for (int i = 0; i < archiveWeightTargetCourseVOS.size(); i++) {
            list.add("教学目标"+(i+1));
        }
        list.add("总分");
        dataList.add((list));
        list= new ArrayList<>();


        //第四行

        list.add("指标点占分");
        list.add("指标点占分");
        for (int i = 0; i < archiveGoalScoreVOS.get(0).getWeights().size(); i++) {
            list.add(String.valueOf(archiveGoalScoreVOS.get(0).getWeights().get(i)*100));
        }
        list.add("100");
        dataList.add((list));
        list= new ArrayList<>();


        //下面几行
        for (ArchiveGoalScoreVO archiveGoalScoreVO : archiveGoalScoreVOS) {
            //写入内容
            list.add(archiveGoalScoreVO.getStuId());
            list.add(archiveGoalScoreVO.getStuName());
            for (int j = 0; j < archiveGoalScoreVO.getScore().size(); j++) {
                list.add(String.valueOf(archiveGoalScoreVO.getScore().get(j)));
            }
            list.add(String.valueOf(archiveGoalScoreVO.getTotal()));
            dataList.add((list));
            list = new ArrayList<>();
        }

        List<List<String>> footer = new ArrayList<>();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        list.add("评价人");
        list.add(archivePointAndTargetVOS.get(0).getTeacher());
        list.add(archivePointAndTargetVOS.get(0).getTeacher());
        list.add(archivePointAndTargetVOS.get(0).getTeacher());
        list.add("评价日期");
        list.add(format.format(date));
        list.add(format.format(date));
        footer.add((list));

        List<Integer> col = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            col.add(i);
        }
        //写入表头
        WriteSheet writeSheet = EasyExcel.writerSheet("总评达成")
                .head(headList)
                .registerWriteHandler(new CustomMergeStrategy(footer, 4 + archiveGoalScoreVOS.size(), null, false, true))
                .registerWriteHandler(new HeightStyle(45.6, "评价人"))
                .registerWriteHandler(new WidthStyle(5000, col))
                .registerWriteHandler(HeadContentCellStyle.myHorizontalCellStyleStrategy())
                .build();

        excelWriter.write(dataList, writeSheet);
        excelWriter.write(footer, writeSheet);
    }

    public static void summary6(ExcelWriter excelWriter){
        List<List<String>> dataList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        List<List<String>> headList = new ArrayList<>();
        List<String> head = new ArrayList<>();
        //设置表头
        String bigTitle = "考核分析表（样本）";
        String title = archivePointAndTargetVOS.get(0).getGrade()+'-'+(Integer.parseInt(archivePointAndTargetVOS.get(0).getGrade())+1)+'-'+archivePointAndTargetVOS.get(0).getSemester();

        int sum = 0;//计算总人数

        sum+= sample.get(0).getExcellent();
        sum+= sample.get(0).getMedium();
        sum+= sample.get(0).getGood();
        sum+= sample.get(0).getFail();
        sum+= sample.get(0).getPass();

        head.add(bigTitle);
        head.add(title);
        head.add("课程名称");
        head.add("任课教师");
        head.add("考核方式");
        headList.add(head);
        head = new ArrayList<>();

        head.add(bigTitle);
        head.add(title);
        head.add(String.valueOf(archivePointAndTargetVOS.get(0).getName()));
        head.add(archivePointAndTargetVOS.get(0).getTeacher());
        head.add(archivePointAndTargetVOS.get(0).getAssessment());
        headList.add(head);
        head = new ArrayList<>();

        head.add(bigTitle);
        head.add(title);
        head.add(String.valueOf(archivePointAndTargetVOS.get(0).getName()));
        head.add(archivePointAndTargetVOS.get(0).getTeacher());
        head.add(archivePointAndTargetVOS.get(0).getAssessment());
        headList.add(head);
        head = new ArrayList<>();

        head.add(bigTitle);
        head.add(title);
        head.add("课程代码");
        head.add("教学班");
        head.add("考试日期");
        headList.add(head);
        head = new ArrayList<>();

        head.add(bigTitle);
        head.add(title);
        head.add("课程代码");
        head.add(archivePointAndTargetVOS.get(0).getTeachClass());
        head.add("考试日期");
        headList.add(head);
        head = new ArrayList<>();

        head.add(bigTitle);
        head.add(title);
        head.add(String.valueOf(archivePointAndTargetVOS.get(0).getSysId()));
        head.add(archivePointAndTargetVOS.get(0).getTeachClass());
        head.add("");
        headList.add(head);
        head = new ArrayList<>();

        head.add(bigTitle);
        head.add(title);
        head.add("性质");
        head.add("学分");
        head.add("人数");
        headList.add(head);
        head = new ArrayList<>();

        head.add(bigTitle);
        head.add(title);
        head.add("核心必修");
        head.add(archivePointAndTargetVOS.get(0).getCredit());
        head.add(String.valueOf(sum));
        headList.add(head);
        head = new ArrayList<>();

        head.add(bigTitle);
        head.add(title);
        head.add("核心必修");
        head.add(archivePointAndTargetVOS.get(0).getCredit());
        head.add(String.valueOf(sum));
        headList.add(head);

        //设置内容
        list.add("");
        list.add("等级");
        list.add("优秀");
        list.add("良好");
        list.add("中等");
        list.add("及格");
        list.add("不及格");
        list.add("目标值");
        list.add("评价值");
        dataList.add((list));
        list= new ArrayList<>();

        //计算总体
        int Excellent=0,Good=0,Medium=0,Pass=0,Fail=0;
        for (int i = 0; i < archiveGoalScoreVOS.size(); i++) {
            if(archiveGoalScoreVOS.get(i).getTotal()>=90) Excellent++;
            else if(archiveGoalScoreVOS.get(i).getTotal()>=80) Good++;
            else if(archiveGoalScoreVOS.get(i).getTotal()>=70) Medium++;
            else if(archiveGoalScoreVOS.get(i).getTotal()>=60) Pass++;
            else Fail++;
        }
        list.add("总体");
        list.add("人数");
        list.add(String.valueOf(Excellent));
        list.add(String.valueOf(Good));
        list.add(String.valueOf(Medium));
        list.add(String.valueOf(Pass));
        list.add(String.valueOf(Fail));
        list.add("目标值");
        list.add("评价值");
        dataList.add((list));
        list = new ArrayList<>();
        list.add("总体");
        list.add("比例");
        list.add(String.valueOf(Excellent/ sum * 100)+ '%');
        list.add(String.valueOf(Good/ sum * 100)+ '%');
        list.add(String.valueOf(Medium/ sum * 100)+ '%');
        list.add(String.valueOf(Pass/ sum * 100)+ '%');
        list.add(String.valueOf(Fail/ sum * 100)+ '%');
        list.add("目标值");
        list.add("评价值");
        dataList.add((list));
        list = new ArrayList<>();

        //下面几行
        for (ArchiveGoalPeopleVO archiveGoalPeopleVO : sample) {
            list.add(archiveGoalPeopleVO.getTargetName());
            list.add("人数");
            list.add(String.valueOf(archiveGoalPeopleVO.getExcellent()));
            list.add(String.valueOf(archiveGoalPeopleVO.getGood()));
            list.add(String.valueOf(archiveGoalPeopleVO.getMedium()));
            list.add(String.valueOf(archiveGoalPeopleVO.getPass()));
            list.add(String.valueOf(archiveGoalPeopleVO.getFail()));
            list.add("1");
            list.add(archiveGoalPeopleVO.getEvaluate());
            dataList.add((list));
            list = new ArrayList<>();
            list.add(archiveGoalPeopleVO.getTargetName());
            list.add("比例");
            list.add(String.valueOf(archiveGoalPeopleVO.getExcellent() / sum * 100) + '%');
            list.add(String.valueOf(archiveGoalPeopleVO.getGood() / sum * 100) + '%');
            list.add(String.valueOf(archiveGoalPeopleVO.getMedium() / sum * 100) + '%');
            list.add(String.valueOf(archiveGoalPeopleVO.getPass() / sum * 100) + '%');
            list.add(String.valueOf(archiveGoalPeopleVO.getFail() / sum * 100) + '%');
            list.add("1");
            list.add(archiveGoalPeopleVO.getEvaluate());
            dataList.add((list));
            list = new ArrayList<>();
        }



        //最后一段
        List<List<String>> foot = new ArrayList<>();
        list = new ArrayList<>();
        list.add("问题和改进措施");
        for (int i = 0; i < 8; i++) {
            list.add(summaryEntity.getImprovement());
        }
        foot.add((list));

        List<Integer> col = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            col.add(i);
        }
        //写入表头
        WriteSheet writeSheet = EasyExcel.writerSheet("考核分析表（样本）")
                .head(headList)
                .registerWriteHandler(new CustomMergeStrategy(headList, 0, 0, false, true))
                .registerWriteHandler(new CustomMergeStrategy(dataList, 5, 0, true, false))
                .registerWriteHandler(new CustomMergeStrategy(foot, 8 + sample.size() * 2, 0, false, true))
                .registerWriteHandler(new HeightStyle(79.5, "问题和改进措施"))
                .registerWriteHandler(new WidthStyle(2000, col))
                .registerWriteHandler(HeadContentCellStyle.myHorizontalCellStyleStrategy())
                .build();

        excelWriter.write(dataList, writeSheet);
        excelWriter.write(foot, writeSheet);
    }

    public static void summary7(ExcelWriter excelWriter){
        List<List<String>> dataList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        List<List<String>> headList = new ArrayList<>();
        List<String> head = new ArrayList<>();

        String bigTitle = "考核分析表（个体）";
        //设置表头
        head.add(bigTitle);
        head.add("课程名称");
        headList.add(head);

        head = new ArrayList<>();
        head.add(bigTitle);
        head.add(archivePointAndTargetVOS.get(0).getName());
        headList.add(head);

        head = new ArrayList<>();
        head.add(bigTitle);
        head.add("课程代码");
        headList.add(head);

        head = new ArrayList<>();
        head.add(bigTitle);
        head.add(String.valueOf(archivePointAndTargetVOS.get(0).getSysId()));
        headList.add(head);

        head = new ArrayList<>();
        head.add(bigTitle);
        head.add("学期");
        headList.add(head);

        head = new ArrayList<>();
        head.add(bigTitle);
        head.add(archivePointAndTargetVOS.get(0).getGrade()+'-'+(Integer.parseInt(archivePointAndTargetVOS.get(0).getGrade())+1)+'-'+archivePointAndTargetVOS.get(0).getSemester());
        headList.add(head);

        head = new ArrayList<>();
        head.add(bigTitle);
        head.add("学分");
        headList.add(head);

        head = new ArrayList<>();
        head.add(bigTitle);
        head.add(archivePointAndTargetVOS.get(0).getCredit());
        headList.add(head);

        //设置内容
        List<ArchiveGoalScoreVO> unit = excelSummaryUtil.archiveGoalScoreService.getUnit(summaryIdUntil);
        int size = unit.get(0).getScore().size(); //教学目标数量
        //第四行
        list.add("学号");
        list.add("姓名");
        for (int i = 0; i < size; i++) {
            list.add("教学目标"+(i+1));
        }
        list.add("总分");
        dataList.add((list));
        list= new ArrayList<>();

        for (ArchiveGoalScoreVO archiveGoalScoreVO : unit) {
            list.add(archiveGoalScoreVO.getStuId());
            list.add(archiveGoalScoreVO.getStuName());
            for (int j = 0; j < size; j++) {
                list.add(String.valueOf(archiveGoalScoreVO.getScore().get(j)));
            }
            list.add(String.valueOf(archiveGoalScoreVO.getTotal()));
            dataList.add((list));
            list = new ArrayList<>();
        }

        List<List<String>> foot = new ArrayList<>();
        list.add("分析说明");
        for (int i = 0; i < size + 2; i++) {
            list.add(summaryEntity.getAnalysisDescription());
        }
        foot.add((list));


        List<Integer> col = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            col.add(i);
        }
        //写入表头
        WriteSheet writeSheet = EasyExcel.writerSheet("考核分析表（个体）")
                .head(headList)
                .registerWriteHandler(new HeightStyle(158.4, "分析说明"))
                .registerWriteHandler(new CustomMergeStrategy(foot, 3 + unit.size(), null, false, true))
                .registerWriteHandler(new WidthStyle(5000, col))
                .registerWriteHandler(HeadContentCellStyle.myHorizontalCellStyleStrategy())
                .build();

        excelWriter.write(dataList, writeSheet);
        excelWriter.write(foot, writeSheet);
    }

    public static void summary8(ExcelWriter excelWriter){
        List<List<String>> dataList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        List<List<String>> headList = new ArrayList<>();
        List<String> head = new ArrayList<>();

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String bigTitle = "课程实施总结——" + archivePointAndTargetVOS.get(0).getName();
        String title = "课程名称："+archivePointAndTargetVOS.get(0).getName() +
                "  学期：" + archivePointAndTargetVOS.get(0).getGrade()+'-'+(Integer.parseInt(archivePointAndTargetVOS.get(0).getGrade())+1)+'-'+archivePointAndTargetVOS.get(0).getSemester() +
                "  任课教师：" + archivePointAndTargetVOS.get(0).getTeacher()+
                "  日期：" + format.format(date);
        //设置表头
        head.add(bigTitle);
        head.add(title);
        headList.add(head);

        head = new ArrayList<>();
        head.add(bigTitle);
        head.add(title);
        headList.add(head);

        head = new ArrayList<>();
        head.add(bigTitle);
        head.add(title);
        headList.add(head);


        //设置内容
        //第三行
        list.add("教学目标");
        list.add("评价");
        list.add("实现途径、考核依据和评价方式");
        dataList.add((list));
        list = new ArrayList<>();

        //第四行
        list.add("教学目标");
        list.add("结果");
        list.add("实现途径、考核依据和评价方式");
        dataList.add((list));
        list = new ArrayList<>();


        for (int i = 0; i < archiveWeightTargetCourseVOS.size(); i++) {
            list.add(archiveWeightTargetCourseVOS.get(i).getTeachTarget());
            list.add(sample.get(i).getEvaluate());
            list.add("达成途径：" + archiveWeightTargetCourseVOS.get(i).getApproach() + "\n" + "评价依据：" + archiveWeightTargetCourseVOS.get(i).getEvaluationBasis() + "\n" + "评价方式：" + archiveWeightTargetCourseVOS.get(i).getEvaluationMethod());
            dataList.add((list));
            list = new ArrayList<>();
        }


        list.add("课程的持续改进");
        list.add("课程的持续改进");
        list.add("课程的持续改进");
        dataList.add((list));
        list = new ArrayList<>();

        list.add("存在问题");
        list.add("课程实施过程中存在的问题（描述去年课程持续改进措施的实施情况和问题，期中教学检查中出现的问题解释，实施过程中存在的困难和问题，结合平时和期末考核情况、前两年的达成度评价情况分析今年达成情况");
        list.add("课程实施过程中存在的问题（描述去年课程持续改进措施的实施情况和问题，期中教学检查中出现的问题解释，实施过程中存在的困难和问题，结合平时和期末考核情况、前两年的达成度评价情况分析今年达成情况");
        dataList.add((list));
        list= new ArrayList<>();

        list.add("存在问题");
        list.add(summaryEntity.getProblem());
        list.add(summaryEntity.getProblem());
        dataList.add((list));
        list= new ArrayList<>();

        list.add("课程改进措施");
        list.add("（从重点分析达成情况的产生的原因，从期中教学检查的问题，考核内容、方式和结果，课堂教学过程等几个角度进行问题描述和持续改进措施撰写）");
        list.add("（从重点分析达成情况的产生的原因，从期中教学检查的问题，考核内容、方式和结果，课堂教学过程等几个角度进行问题描述和持续改进措施撰写）");
        dataList.add((list));
        list= new ArrayList<>();

        list.add("课程改进措施");
        list.add(summaryEntity.getMeasures());
        list.add(summaryEntity.getMeasures());
        dataList.add((list));
        list= new ArrayList<>();


        list.add("其他可用的协助持续改进的资源");
        list.add("对前置课程的建议和后置课程的期望");
        list.add("对前置课程的建议和后置课程的期望");
        dataList.add((list));
        list= new ArrayList<>();

        list.add("其他可用的协助持续改进的资源");
        list.add(summaryEntity.getResources());
        list.add(summaryEntity.getResources());
        dataList.add((list));
        list = new ArrayList<>();

        list.add("系主任意见");
        list.add("见系总结汇总表");
        list.add("见系总结汇总表");
        dataList.add((list));

        List<Integer> col = new ArrayList<>();
        col.add(0);
        col.add(2);
        //写入表头
        WriteSheet writeSheet = EasyExcel.writerSheet("课程实施总结表")
                .head(headList)
                .registerWriteHandler(new CustomMergeStrategy(dataList,2,0,true,true))
                .registerWriteHandler(new HeightStyle(30,"课程的持续改进"))
                .registerWriteHandler(new HeightStyle(46.5,"系主任意见"))
                .registerWriteHandler(new HeightStyle(46,"存在问题"))
                .registerWriteHandler(new HeightStyle(44,"课程改进措施"))
                .registerWriteHandler(new HeightStyle(30,"其他可用的协助持续改进的资源"))
                .registerWriteHandler(new WidthStyle(10000, col))
                .registerWriteHandler(HeadContentCellStyle.myHorizontalCellStyleStrategy())
                .build();

        excelWriter.write(dataList, writeSheet);

    }

}


