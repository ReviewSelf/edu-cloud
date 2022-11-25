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
import net.edu.module.dao.ArchiveWeightGoalDao;
import net.edu.module.dao.ArchiveWeightTargetCourseDao;
import net.edu.module.entity.ArchiveWeightGoalEntity;
import net.edu.module.service.ArchiveGoalScoreService;
import net.edu.module.service.ArchiveWeightTargetCourseService;
import net.edu.module.vo.ArchiveGoalPeopleVO;
import net.edu.module.vo.ArchiveGoalScoreVO;
import net.edu.module.vo.ArchivePointAndTargetVO;
import net.edu.module.vo.ArchiveWeightTargetCourseVO;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@Service
@AllArgsConstructor
public class ExcelSummaryUtil {
    @Autowired
    private  ArchiveWeightTargetCourseService archiveWeightTargetCourseService;
    @Autowired
    private ArchiveGoalScoreService archiveGoalScoreService;
    @Autowired
    private ArchiveWeightTargetCourseDao archiveWeightTargetCourseDao;

    private static ExcelSummaryUtil excelSummaryUtil;

    private static Long courseIdUntil;

    @PostConstruct
    public void init() {
        excelSummaryUtil = this;
        excelSummaryUtil.archiveWeightTargetCourseDao = this.archiveWeightTargetCourseDao;
        excelSummaryUtil.archiveWeightTargetCourseService = this.archiveWeightTargetCourseService;
        excelSummaryUtil.archiveGoalScoreService = this.archiveGoalScoreService;
    }


    public static void excelSummaryUtil(Long courseId,HttpServletResponse response) throws IOException {

        courseIdUntil=courseId;
        System.out.println("courseIdUntil"+courseIdUntil);

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
                .registerWriteHandler(horizontalCellStyleStrategy)
                .build();
        //生成工作簿
        summary1(excelWriter);
        summary2(excelWriter);
        summary5(excelWriter);
        summary6(excelWriter);
        excelWriter.finish();
        response.flushBuffer();
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
        //设置表头
        //写入表头
        WriteSheet writeSheet = EasyExcel.writerSheet("测试2")
                .registerWriteHandler(new CellRowHeightStyleStrategy())
                .registerWriteHandler(new SimpleColumnWidthStyleStrategy(25))
                .registerWriteHandler(HeadContentCellStyle.myHorizontalCellStyleStrategy())
                .build();
        //设置内容
        for (int j = 0; j < 10; j++) {
            List<String> list = new ArrayList<>();
            list.add("1");
            list.add("2");
            list.add("3");
            dataList.add(list);
            //写入内容
            System.out.println(list);
            excelWriter.write(dataList, writeSheet);
            dataList = new ArrayList<>();
        }
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("毕业要求2（问题分析）：能够应用数学、自然科学和工程科学的基本原理，识别、表达和分析计算机专业领域复杂工程问题，并通过文献研究获取相关信息，整理、分析和归纳资料，以获得有效结论。");
        dataList.add(list);
        excelWriter.write(dataList,writeSheet);

    }

    public static void summary5(ExcelWriter excelWriter){
        List<String> list = new ArrayList<>();
        //设置表头
        List<ArchivePointAndTargetVO> archivePointAndTargetVOS= excelSummaryUtil.archiveWeightTargetCourseService.selectPointAndTarget(courseIdUntil);
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

        //写入表头
        WriteSheet writeSheet = EasyExcel.writerSheet("总评达成")
                .head(headList)
                .registerWriteHandler(new CellRowHeightStyleStrategy())
                .registerWriteHandler(new SimpleColumnWidthStyleStrategy(25))
                .registerWriteHandler(HeadContentCellStyle.myHorizontalCellStyleStrategy())
                .build();
        //设置内容

        List<ArchiveGoalScoreVO> archiveGoalScoreVOS = excelSummaryUtil.archiveGoalScoreService.selectGoalScoreByCourseId(courseIdUntil);
        //第三行
        List<ArchiveWeightTargetCourseVO> WeightVOS = excelSummaryUtil.archiveWeightTargetCourseDao.selectCourseByCourseId(courseIdUntil);
        list.add("");
        list.add("题目类型");
        for (int i = 0; i < WeightVOS.size(); i++) {
            list.add("教学目标"+(i+1));
        }
        list.add("总分");
        dataList.add((list));
        excelWriter.write(dataList, writeSheet);
        list= new ArrayList<>();
        dataList= new ArrayList<>();

        //第四行

        list.add("");
        list.add("指标点占分");
        for (int i = 0; i < archiveGoalScoreVOS.get(0).getWeights().size(); i++) {
            list.add(archiveGoalScoreVOS.get(0).getWeights().get(i).toString());
        }
        list.add("100");
        dataList.add((list));
        excelWriter.write(dataList, writeSheet);
        list= new ArrayList<>();
        dataList= new ArrayList<>();


        //下面几行
        for (ArchiveGoalScoreVO archiveGoalScoreVO : archiveGoalScoreVOS) {
            System.out.println(archiveGoalScoreVO);
            //写入内容
            list.add(archiveGoalScoreVO.getStuId());
            list.add(archiveGoalScoreVO.getStuName());
            for (int j = 0; j < archiveGoalScoreVO.getScore().size(); j++) {
                list.add(String.valueOf(archiveGoalScoreVO.getScore().get(j)));
            }
            list.add(String.valueOf(archiveGoalScoreVO.getTotal()));
            dataList.add((list));
            excelWriter.write(dataList, writeSheet);
            list = new ArrayList<>();
            dataList = new ArrayList<>();
        }
        excelWriter.write(dataList,writeSheet);

    }

    public static void summary6(ExcelWriter excelWriter){
        List<List<String>> dataList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        //设置表头
        List<ArchivePointAndTargetVO> archivePointAndTargetVOS= excelSummaryUtil.archiveWeightTargetCourseService.selectPointAndTarget(courseIdUntil);
        //写入表头
        WriteSheet writeSheet = EasyExcel.writerSheet("考核分析表（样本）")
                .registerWriteHandler(new CellRowHeightStyleStrategy())
                .registerWriteHandler(new SimpleColumnWidthStyleStrategy(25))
                .registerWriteHandler(HeadContentCellStyle.myHorizontalCellStyleStrategy())
                .build();
        //设置内容

        //第一行内容
        list.add(String.valueOf(archivePointAndTargetVOS.get(0).getSemester()));
        dataList.add((list));
        excelWriter.write(dataList, writeSheet);
        list= new ArrayList<>();
        dataList= new ArrayList<>();

        //第二行内容
        System.out.println(archivePointAndTargetVOS);
        list.add("课程名称");
        list.add(String.valueOf(archivePointAndTargetVOS.get(0).getName()));
        list.add("教学编号");
        list.add(String.valueOf(archivePointAndTargetVOS.get(0).getSysId()));
        list.add("性质");
        list.add("核心必修");
        dataList.add((list));
        excelWriter.write(dataList, writeSheet);
        list= new ArrayList<>();
        dataList= new ArrayList<>();


        //第三行内容
        list.add("任课教师");
        list.add(archivePointAndTargetVOS.get(0).getTeacher());
        list.add("教学班");
        list.add(archivePointAndTargetVOS.get(0).getTeachClass());
        list.add("学分");
        list.add(archivePointAndTargetVOS.get(0).getCredit());
        dataList.add((list));
        excelWriter.write(dataList, writeSheet);
        list= new ArrayList<>();
        dataList= new ArrayList<>();

        //第四行内容
        list.add("考核方式");
        list.add(archivePointAndTargetVOS.get(0).getAssessment());
        list.add("考试日期");
        list.add("");
        list.add("人数");
        list.add(String.valueOf(archivePointAndTargetVOS.size()));
        dataList.add((list));
        excelWriter.write(dataList, writeSheet);
        list= new ArrayList<>();
        dataList= new ArrayList<>();

        //第五行内容
        list.add("");
        list.add("等级");
        list.add("优秀");
        list.add("良好");
        list.add("中等");
        list.add("及格");
        list.add("不及格");
        dataList.add((list));
        excelWriter.write(dataList, writeSheet);
        list= new ArrayList<>();
        dataList= new ArrayList<>();

        List<ArchiveGoalPeopleVO> sample = excelSummaryUtil.archiveGoalScoreService.getSample(courseIdUntil);
        //下面几行
        for (int i = 0; i < sample.size(); i++) {
            list.add(sample.get(i).getTargetName());
            list.add("人数");
            list.add(String.valueOf(sample.get(i).getExcellent()));
            list.add(String.valueOf(sample.get(i).getGood()));
            list.add(String.valueOf(sample.get(i).getMedium()));
            list.add(String.valueOf(sample.get(i).getPass()));
            list.add(String.valueOf(sample.get(i).getFail()));
            dataList.add((list));
            excelWriter.write(dataList, writeSheet);
            list= new ArrayList<>();
            dataList= new ArrayList<>();
        }



        excelWriter.write(dataList,writeSheet);

    }
}
