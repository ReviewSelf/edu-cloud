package net.edu.module.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import lombok.extern.slf4j.Slf4j;
import net.edu.module.vo.ExamExcelVo;
import net.edu.module.vo.ExamUserExcelVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @Author: 樊磊
 * @Date: 2022/10/31 15:51
 * @Version: 2.0
 * @Description:考试详情excel相关工具类
 */
@Component
@Slf4j
public class ExamProblemInfoExcelUtil {

    public void examExportExcel(List<ExamUserExcelVo> data, List<String> bigTitleList, HttpServletResponse response) throws IOException {
        String name = StringUtils.substringBetween(bigTitleList.get(0), "《", "》");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name+"部分学生.xlsx", "UTF-8"));
        response.setContentType("application/vnd.ms-excel; charset=utf-8");
        response.setCharacterEncoding("utf-8");

        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();

        List<List<String>> dataList = new ArrayList<>();
        for (int i = 0; i < bigTitleList.size(); i++) {
            //设置表头
            List<List<String>> head =  setHead(bigTitleList.get(i));
            //写入表头
            WriteSheet writeSheet = EasyExcel.writerSheet(i, data.get(i).getName()).
                    head(head)
                    .registerWriteHandler(new CellRowHeightStyleStrategy())
                    .registerWriteHandler(new SimpleColumnWidthStyleStrategy(25))
                    .registerWriteHandler(HeadContentCellStyle.myHorizontalCellStyleStrategy())
                    .build();
            //设置内容
            for (int j = 0; j < data.get(i).getProblemInfoList().size(); j++) {
                List<String> list = new ArrayList<>();
                list.add(data.get(i).getProblemInfoList().get(j).getProblemName());
                list.add(data.get(i).getProblemInfoList().get(j).getSubmitContent());
                list.add(String.valueOf(data.get(i).getProblemInfoList().get(j).getFraction()));
                dataList.add(list);
                //写入内容
                excelWriter.write(dataList, writeSheet);
                dataList = new ArrayList<>();
            }

        }
        excelWriter.finish();
        response.flushBuffer();
    }

    public List<List<String>> setHead(String bigTitleItem){
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
