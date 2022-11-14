package net.edu.module.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import lombok.extern.slf4j.Slf4j;
import net.edu.module.vo.ArchiveExamAttendLogVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 樊磊
 * @Date: 2022/10/31 15:51
 * @Version: 1.0
 * @Description:考试excel相关工具类
 */
@Component
@Slf4j
public class ExamExcelUtil {

    public static void examExportExcel(List<String> header, List<ArchiveExamAttendLogVO> data, String bigTitle, HttpServletResponse response) throws IOException {
        String name = StringUtils.substringBetween(bigTitle, "《", "》");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("考试成绩.xlsx", "UTF-8"));
        response.setContentType("application/vnd.ms-excel; charset=utf-8");
        response.setCharacterEncoding("utf-8");

        EasyExcel.write(response.getOutputStream())
                .head(getExcelHeader(header, bigTitle))
                .registerWriteHandler(new CellRowHeightStyleStrategy())
                .registerWriteHandler(new SimpleColumnWidthStyleStrategy(20))
                .registerWriteHandler(HeadContentCellStyle.myHorizontalCellStyleStrategy())
                .sheet(name).doWrite(getExamExcelData(data));
    }

    /**
     * 生成表头
     *
     * @param header
     * @param bigTitle
     * @return
     */
    public static List<List<String>> getExcelHeader(List<String> header, String bigTitle) {
        List<List<String>> head = new ArrayList<>();

        List<String> childHead = new ArrayList<>();
        childHead.add(bigTitle);
        childHead.add("学号");
        head.add(childHead);

        childHead = new ArrayList<>();
        childHead.add(bigTitle);
        childHead.add("姓名");
        head.add(childHead);


        childHead = new ArrayList<>();
        childHead.add(bigTitle);
        childHead.add("总分");
        head.add(childHead);
        return head;
    }

    /**
     * 生成表格数据
     *
     * @param vo
     * @return
     */
    public static List<List<String>> getExamExcelData(List<ArchiveExamAttendLogVO> vo) {
        List<List<String>> dataList = new ArrayList<>();
        for (int i = 0; i < vo.size(); i++) {
            List<String> list = new ArrayList<>();
            list.add(vo.get(i).getUserNumber().toString());
            list.add(vo.get(i).getUserName());
            list.add(vo.get(i).getScore().toString());
            dataList.add(list);
        }
        return dataList;
    }

}
