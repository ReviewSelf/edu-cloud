package net.edu.module.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.edu.module.service.SampleUploadService;
import net.edu.module.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/28 15:05
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/template")
@Tag(name = "模板文件下载")
public class TemplateController {
    @Autowired
    private TemplateService templateService;

    @GetMapping("/problemExcel/{type}")
    public void  downloadProblemImportExcel(@PathVariable("type") Integer type, HttpServletResponse response){
        templateService.downloadProblemImportExcel(type,response);
    }

    @GetMapping("/studentExcel")
    public void  downloadStudentImportExcel(HttpServletResponse response){
        templateService.downloadStudentImportExcel(response);
    }

    @GetMapping("/archiveExcel")
    public void  downloadArchiveImportExcel(HttpServletResponse response){
        templateService.downloadArchiveImportExcel(response);
    }

}
