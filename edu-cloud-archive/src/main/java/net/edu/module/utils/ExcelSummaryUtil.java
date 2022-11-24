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
import net.edu.module.service.ArchiveWeightTargetCourseService;
import net.edu.module.vo.ArchivePointAndTargetVO;
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


    private static ExcelSummaryUtil excelSummaryUtil;

    private static Long courseIdUntil;

    @PostConstruct
    public void init() {
        excelSummaryUtil = this;
        excelSummaryUtil.archiveWeightTargetCourseService = this.archiveWeightTargetCourseService;
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

}
