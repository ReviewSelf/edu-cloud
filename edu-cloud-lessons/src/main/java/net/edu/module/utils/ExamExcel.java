package net.edu.module.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import lombok.extern.slf4j.Slf4j;
import net.edu.module.vo.ExamScoreVO;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
public class ExamExcel {

    public void examExportExcel(List<String> header,List<ExamScoreVO> data,String bigTitle, HttpServletResponse response)throws IOException {
        response.setHeader("Content-Disposition", "attachment; filename=Zjlryshz.xlsx");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        EasyExcel.write(response.getOutputStream()).head(getExcelHeader(header,bigTitle)).sheet().doWrite(getExamExcelData(data));
    }

    public List<List<String>> getExcelHeader(List<String> header,String bigTitle){
        List<List<String>> head = new ArrayList<>();
        List<String> head0 = new ArrayList<>();
        head0.add(bigTitle);
        head0.add("姓名");
        for (String i:header){
            head0 = new ArrayList<>();
            head0.add(bigTitle);
            head0.add(i);
            head.add(head0);
        }
        head0.add(bigTitle);
        head0.add("总分");
        return head;
    }

    public List<List<Object>> getExamExcelData(List<ExamScoreVO> vo){
        List<List<Object>> dataList = new ArrayList<>();
        for (int i = 0;i<vo.size();i++){
            List<Object> list = new ArrayList<>();
            list.add(vo.get(i).getName());
            for (int j = 0;j<vo.get(i).getProblemRecords().size();j++){
                list.add(vo.get(j).getProblemRecords().get(j).getFraction());
            }
            dataList.add(list);
        }
        return dataList;
    }

}
