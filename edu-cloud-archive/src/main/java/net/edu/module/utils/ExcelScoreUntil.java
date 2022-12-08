package net.edu.module.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 沈凯文
 * @Date: 2022/11/17
 * @Version: 1.0
 * @Description:考试excel相关工具类
 */
@Component
@Slf4j
public class ExcelScoreUntil {

    public static void excelScoreUntil(HttpServletResponse response) throws IOException {
        String name = StringUtils.substringBetween("平时成绩", "《", "》");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("考试成绩.xlsx", "UTF-8"));
        response.setContentType("application/vnd.ms-excel; charset=utf-8");
        response.setCharacterEncoding("utf-8");

        EasyExcel.write(response.getOutputStream())
                .registerWriteHandler(new CellRowHeightStyleStrategy())
                .registerWriteHandler(new SimpleColumnWidthStyleStrategy(20))
                .registerWriteHandler(HeadContentCellStyle.myHorizontalCellStyleStrategy())
                .sheet(name);
    }

    /**
     * 生成表头
     *
     * @param header
     * @param bigTitle
     * @return
     */
    public static List<List<String>> getExcelHeader(List<String> header, String bigTitle) {
        System.out.println(header);
        List<List<String>> head = new ArrayList<>();

        List<String> childHead = new ArrayList<>();
        childHead.add(bigTitle);
        childHead.add("学号");
        head.add(childHead);

        childHead = new ArrayList<>();
        childHead.add(bigTitle);
        childHead.add("姓名");
        head.add(childHead);


        for (String i : header) {
            childHead = new ArrayList<>();
            childHead.add(bigTitle);
            childHead.add(i);
            head.add(childHead);
        }

        System.out.println(head);
        return head;
    }


}
