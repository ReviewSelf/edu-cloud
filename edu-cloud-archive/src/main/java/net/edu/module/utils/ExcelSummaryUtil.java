package net.edu.module.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import lombok.extern.slf4j.Slf4j;
import net.edu.framework.common.excel.HeadContentCellStyle;
import net.edu.framework.common.utils.ResponseHeadUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ExcelSummaryUtil {

    public static void excelSummaryUtil(HttpServletResponse response) throws IOException {
        String name = StringUtils.substringBetween("测试", "《", "》")+"部分学生.xlsx";
        ResponseHeadUtils.responseEXCELHead(response,name);


        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();

        List<List<String>> dataList = new ArrayList<>();
        //设置表头
        List<List<String>> head =  setHead("111111");
        //写入表头
        WriteSheet writeSheet = EasyExcel.writerSheet("测试").
                head(head)
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
            excelWriter.write(dataList, writeSheet);
            dataList = new ArrayList<>();
        }
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
