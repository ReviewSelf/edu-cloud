package net.edu.module.service;

import lombok.SneakyThrows;
import net.edu.framework.common.exception.ServerException;
import net.edu.module.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/28 15:06
 * @Version: 1.0
 * @Description:
 */
@Service
public class TemplateService {
    @Value("${storage.local.templatePath}")
    String templatePath;


    @SneakyThrows
    public void downloadProblemImportExcel(Integer type, HttpServletResponse response){
        String path=templatePath+ File.separator;
        String name="";
        if(type==1){
            path+="choiceImportExcel.xlsx";
            name="选择题导入模板文件.xlsx";
        }
        else if(type==2){
            path+="fillImportExcel.xlsx";
            name="判断题导入模板文件.xlsx";
        }
        else if (type==3) {
            path+="codeImportExcel.xlsx";
            name="代码题导入模板文件.xlsx";
        }

        File file = new File(path);
        if (!file.exists()) {
            throw new ServerException("文件不存在");
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStream fis = new BufferedInputStream(fileInputStream);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        ResponseUtils.responseEXCELHead(response, name);
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        outputStream.write(buffer);
        outputStream.flush();
    }
}
