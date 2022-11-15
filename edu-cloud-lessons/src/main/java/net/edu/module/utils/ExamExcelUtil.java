package net.edu.module.utils;

import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;
import net.edu.framework.common.excel.HeadContentCellStyle;
import net.edu.framework.common.utils.ResponseHeadUtils;
import net.edu.module.vo.ExamScoreVO;
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

    public static void examExportExcel(List<String> header, List<ExamScoreVO> data, String bigTitle, HttpServletResponse response) throws IOException {
        String name = StringUtils.substringBetween(bigTitle, "《", "》") + "考试情况.xlsx";
        ResponseHeadUtils.responseEXCELHead(response,name);

        EasyExcel.write(response.getOutputStream())
                .head(getExcelHeader(header, bigTitle))
                .registerWriteHandler(new CellRowHeightStyleStrategy())
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

        for (String i : header) {
            childHead = new ArrayList<>();
            childHead.add(bigTitle);
            childHead.add(i);
            head.add(childHead);
        }
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
    public static List<List<String>> getExamExcelData(List<ExamScoreVO> vo) {
        List<List<String>> dataList = new ArrayList<>();
        for (int i = 0; i < vo.size(); i++) {
            List<String> list = new ArrayList<>();
            list.add(vo.get(i).getUsername());
            list.add(vo.get(i).getName());
            BigDecimal sum = new BigDecimal(0.00);
            for (int j = 0; j < vo.get(i).getProblemRecords().size(); j++) {
                //获取每道题分数
                BigDecimal fraction = vo.get(i).getProblemRecords().get(j).getFraction();
                //计算总分
                list.add(String.valueOf(fraction));
                sum = sum.add(fraction);
            }
            list.add(String.valueOf(sum));
            dataList.add(list);
        }
        return dataList;
    }

}
