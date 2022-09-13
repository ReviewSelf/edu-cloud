package net.edu.module.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import net.edu.framework.common.exception.ServerException;
import net.edu.framework.common.utils.EncryptUtils;
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
        List<SampleVO> sampleVOS=new ArrayList<>();
        String newFileName = problemId + File.separator + System.currentTimeMillis();
        // 文件扩展名
        for (int i=0;i<input.length;i++){
            String inputPath=  sampleUploadService.upload(input[i], newFileName+"_"+i + ".in");
            String outPath=sampleUploadService.upload(output[i], newFileName+"_"+i + ".out");
            sampleVOS.add(new SampleVO(problemId,inputPath,outPath,input[i].getSize(),output[i].getSize()));
        }
        return sampleVOS;
    }

    @PostMapping("/upload")
    @Operation(summary = "一个测试样例文件")
    public FileUploadVO upload(@RequestParam("file") MultipartFile file, @RequestParam("problemId") String problemId) {
        FileUploadVO fileUploadVO=new FileUploadVO();
        if (file.isEmpty()) {
            throw new RuntimeException("文件为空");
        }
        String newFileName = problemId + File.separator + System.currentTimeMillis();
        // 文件扩展名
        String suffix = FileNameUtil.getSuffix(file.getOriginalFilename());

        if ("in".equals(suffix)) {
            //输入
            fileUploadVO.setUrl(sampleUploadService.upload(file, newFileName + ".in"));
            fileUploadVO.setSize(file.getSize());
            fileUploadVO.setName(file.getOriginalFilename());
        } else if ("out".equals(suffix)) {
            //输出
            fileUploadVO.setUrl(sampleUploadService.upload(file, newFileName + ".out"));
            fileUploadVO.setSize(file.getSize());
            fileUploadVO.setName(file.getOriginalFilename());
        } else {
            throw new ServerException("只能上传in和out格式文件");
        }
        return fileUploadVO;
    }

    @SneakyThrows
    @RequestMapping("/download")
    @Operation(summary = "下载测试样例文件")
    public void download(@RequestParam("path") String path, HttpServletResponse response) {
        File file = new File(path);
        if (!file.exists()) {
            throw new ServerException("文件不存在");
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStream fis = new BufferedInputStream(fileInputStream);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        ResponseUtils.responseFileHead(response, file.getName());
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        outputStream.write(buffer);
        outputStream.flush();

    }


    @SneakyThrows
    @GetMapping("/fileBase64")
    @Operation(summary = "测试样例转Base64")
    public String getFileContent(@RequestParam("path") String path){
        return EncryptUtils.getFileBase64(path);
    }
}

