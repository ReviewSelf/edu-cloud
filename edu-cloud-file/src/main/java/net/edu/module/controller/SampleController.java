package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import net.edu.framework.common.utils.Result;
import net.edu.module.service.SampleUploadService;
import net.edu.module.vo.FileUploadVO;
import net.edu.module.vo.SampleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
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

    @SneakyThrows
    @GetMapping("/getFileList")
    @Operation(summary = "根据路径返回文件集合")
    public Result<List<File>> getFileList(@RequestParam("pathList") List<String> pathList){
        return Result.ok(sampleUploadService.getFileList(pathList));
    }



}

