package net.edu.module.utils;

import com.alibaba.excel.EasyExcel;
import net.edu.module.vo.ExamScoreVO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ExamProblemInfoExcelUtil {

    public void examExportExcel(List<String> header, List<ExamScoreVO> data, String bigTitle, HttpServletResponse response)throws IOException {

        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("name.xlsx", "UTF-8"));
        response.setContentType("application/vnd.ms-excel; charset=utf-8");
        response.setCharacterEncoding("utf-8");

        EasyExcel.write(response.getOutputStream())
                .head(getExcelHeader(bigTitle))
                .registerWriteHandler(new CellRowHeightStyleStrategy())
                .registerWriteHandler(HeadContentCellStyle.myHorizontalCellStyleStrategy())
                .sheet("成绩单").doWrite(getExamExcelData(data));
    }

    /**
     * 生成表头
     * @param bigTitle
     * @return
     */
    public List<List<String>> getExcelHeader(String bigTitle){
        List<List<String>> head = new ArrayList<>();
        List<String> childHead = new ArrayList<>();
        childHead.add(bigTitle);
        childHead.add("题目内容");
        head.add(childHead);
        childHead = new ArrayList<>();
        childHead.add(bigTitle);
        childHead.add("答题内容");
        head.add(childHead);
        childHead = new ArrayList<>();
        childHead.add(bigTitle);
        childHead.add("得分");
        head.add(childHead);
        return head;
    }

    /**
     * 生成表格数据
     * @param vo
     * @return
     */
    public List<List<String>> getExamExcelData(List<ExamScoreVO> vo){
        List<List<String>> dataList = new ArrayList<>();


        return dataList;
    }
}
