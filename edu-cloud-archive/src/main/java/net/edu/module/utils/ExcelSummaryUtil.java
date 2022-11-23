package net.edu.module.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.edu.framework.common.excel.HeadContentCellStyle;
import net.edu.framework.common.utils.ResponseHeadUtils;
import net.edu.module.service.ArchiveWeightTargetCourseService;
import org.apache.commons.lang3.StringUtils;
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

    @PostConstruct
    public void init() {
        excelSummaryUtil = this;
        excelSummaryUtil.archiveWeightTargetCourseService = this.archiveWeightTargetCourseService;
    }


    public static void excelSummaryUtil(HttpServletResponse response) throws IOException {

        System.out.println(excelSummaryUtil.archiveWeightTargetCourseService.selectCourseByCourseId(2L));

        String name = StringUtils.substringBetween("测试", "《", "》")+"部分学生.xlsx";
        ResponseHeadUtils.responseEXCELHead(response,name);


        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();

        List<List<String>> dataList = new ArrayList<>();
        //设置表头
        List<List<String>> head =  setHead("111111");
        //写入表头
        WriteSheet writeSheet = EasyExcel.writerSheet("测试")
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
        excelWriter.finish();
        response.flushBuffer();
    }

    public static List<List<String>> setHead(String bigTitleItem){
        List<List<String>> head = new ArrayList<>();
        List<String> childHead = new ArrayList<>();
        //设置表头
        childHead.add(bigTitleItem);
        childHead.add("题目");
        head.add(childHead);
        childHead = new ArrayList<>();
        childHead.add(bigTitleItem);
        childHead.add("答题内容");
        head.add(childHead);
        childHead = new ArrayList<>();
        childHead.add(bigTitleItem);
        childHead.add("得分");
        head.add(childHead);
        return head;
    }

}
