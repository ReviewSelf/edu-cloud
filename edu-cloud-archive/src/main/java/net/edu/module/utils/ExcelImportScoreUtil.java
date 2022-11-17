package net.edu.module.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import lombok.extern.slf4j.Slf4j;
import net.edu.module.dao.ArchiveTestScoreDao;
import net.edu.module.vo.ArchiveExamAttendLogVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: 樊磊
 * @Date: 2022/10/31 15:51
 * @Version: 1.0
 * @Description:考试excel相关工具类
 */
@Component
@Slf4j
public class ExcelImportScoreUtil {
    @Autowired
    private static ArchiveTestScoreDao archiveTestScoreDao;

    public static void ExcelImportScoreUtil(List<Map<Integer, String>> list) {
        System.out.println(list);
        int testLength=0;
        for(int i=2;i<list.get(0).size();i++){
            if(list.get(0).get(i)==null){
                break;
            }
            System.out.println(list.get(0).get(i));
            testLength++;
        }
        for(int i=1;i<list.size();i++){
            String stuId=list.get(i).get(0);
            String stuName=list.get(i).get(1);
            System.out.println(stuId+" "+stuName);
            List scoreList=new ArrayList();
            for(int j=2;j<testLength+2;j++){
                scoreList.add(list.get(i).get(j));
            }
            System.out.println(scoreList);
//            archiveTestScoreDao.insertArchiveTestScore(stuId,stuName,scoreList);
        }
    }
}
