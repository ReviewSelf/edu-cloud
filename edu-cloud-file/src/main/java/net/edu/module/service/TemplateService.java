package net.edu.module.service;

import lombok.SneakyThrows;
import net.edu.framework.common.exception.ServerException;
import net.edu.framework.common.utils.ResponseHeadUtils;

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

    private final int CHOICE_TYPE=1;
    private final int FILL_TYPE=2;
    private final int CODE_TYPE=3;


    @SneakyThrows
    public void downloadArchiveImportExcel(HttpServletResponse response){
        String path=templatePath+ File.separator+"archiveImportExcel.xlsx";
        String name="能力导入模板文件.xlsx";
        ResponseHeadUtils.responseEXCELHead(response, name);
        outputFile(response,path);
    }
    @SneakyThrows
    public void downloadArchiveScoreBookImportExcel(HttpServletResponse response){
        String path=templatePath+ File.separator+"archiveScoreBookImportExcel.xlsx";
        String name="平时记录导入模板文件.xlsx";
        ResponseHeadUtils.responseEXCELHead(response, name);
        outputFile(response,path);
    }


    @SneakyThrows
    public void downloadStudentImportExcel(HttpServletResponse response){
        String path=templatePath+ File.separator+"studentImportExcel.xlsx";
        String name="学生导入模板文件.xlsx";
        ResponseHeadUtils.responseEXCELHead(response, name);
        outputFile(response,path);
    }

    @SneakyThrows
    public void downloadProblemImportExcel(Integer type, HttpServletResponse response){
        String path=templatePath+ File.separator;
        String name="";
        if(type==CHOICE_TYPE){
            path+="choiceImportExcel.xlsx";
            name="选择题导入模板文件.xlsx";
        }
        else if(type==FILL_TYPE){
            path+="fillImportExcel.xlsx";
            name="填空题导入模板文件.xlsx";
        }
        else if (type==CODE_TYPE) {
            path+="codeImportExcel.xlsx";
            name="代码题导入模板文件.xlsx";
        }
        ResponseHeadUtils.responseEXCELHead(response, name);
        outputFile(response,path);
    }

    @SneakyThrows
    public void outputFile(HttpServletResponse response, String path){
        File file = new File(path);
        if (!file.exists()) {
            throw new ServerException("文件不存在");
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStream fis = new BufferedInputStream(fileInputStream);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        outputStream.write(buffer);
        outputStream.flush();
    }
}
