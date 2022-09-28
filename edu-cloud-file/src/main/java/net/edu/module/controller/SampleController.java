package net.edu.module.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import net.edu.framework.common.exception.ServerException;
import net.edu.framework.common.utils.EncryptUtils;
import net.edu.framework.common.utils.Result;
import net.edu.module.service.SampleUploadService;
import net.edu.module.utils.ResponseUtils;
import net.edu.module.vo.FileUploadVO;
import net.edu.module.vo.SampleVO;
import org.apache.ibatis.javassist.tools.reflect.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/sample")
@Tag(name = "文件上传")
public class SampleController {

    @Autowired
    private SampleUploadService sampleUploadService;


    @PostMapping("/upload/batch")
    @Operation(summary = "一个测试样例文件")
    public List<SampleVO> uploadBatch(@RequestParam("input") MultipartFile[] input, @RequestParam("output") MultipartFile[] output, @RequestParam("problemId") Long problemId) {
        return sampleUploadService.uploadBatch(input, output, problemId);
    }

    @PostMapping("/upload")
    @Operation(summary = "一个测试样例文件")
    public FileUploadVO upload(@RequestParam("file") MultipartFile file, @RequestParam("problemId") String problemId) {
        return null;
    }

    @SneakyThrows
    @RequestMapping("/download")
    @Operation(summary = "下载测试样例文件")
    public void download(@RequestParam("path") String path, HttpServletResponse response) {
        sampleUploadService.download(path,response);

    }


    @SneakyThrows
    @GetMapping("/fileBase64")
    @Operation(summary = "测试样例转Base64")
    public Result<String> getFileContent(@RequestParam("path") String path){
        return Result.ok(sampleUploadService.getFileContent(path));
    }

}

