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
import java.util.Objects;

/**
 * @Author: 樊磊
 * @Date: 2022/10/31 15:51
 * @Version: 1.0
 * @Description:考试excel相关工具类
 */
@Component
@Slf4j
public class ExamExcelUtil {

    public static void examExportExcel(List<String> header, List<List<ArchiveExamAttendLogVO>> data, String bigTitle, HttpServletResponse response) throws IOException {
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
                .sheet(name)
                .doWrite(getExamExcelData(data));
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

    /**
     * 生成表格数据
     *
     * @param vo
     * @return
     */
    public static List<List<String>> getExamExcelData(List<List<ArchiveExamAttendLogVO>> vo) {
        System.out.println(vo);
        List<List<String>> dataList = new ArrayList<>();

        for (List<ArchiveExamAttendLogVO> archiveExamAttendLogVOS : vo) {

            for (ArchiveExamAttendLogVO archiveExamAttendLogVO : archiveExamAttendLogVOS) {
                List<String> list = new ArrayList<>();
                list.add(archiveExamAttendLogVO.getUserNumber().toString());
                list.add(archiveExamAttendLogVO.getUserName());
                list.add(archiveExamAttendLogVO.getScore().toString());
                dataList.add(list);
            }

        }
        int len = vo.get(0).size();
//        System.out.println(dataList);
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < dataList.size()/vo.size(); i++) {
            if(Objects.equals(dataList.get(i).get(0), dataList.get(i + len).get(0))){
                dataList.get(i).add(dataList.get(i+len).get(2));
                ans.add(dataList.get(i));
            }
        }
        System.out.println(ans);
        return ans;
    }

}
