package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.edu.framework.common.utils.Result;
import net.edu.module.service.PreviewResourceService;
import net.edu.module.service.SampleUploadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@RestController
@Tag(name = "文件预览")
@RequestMapping("/preview")
@AllArgsConstructor
public class PreviewResourceController {

    private final PreviewResourceService previewResourceService;

    @SneakyThrows
    @GetMapping("/pdf1")
    @Operation(summary = "资源文件转Base64")
    public Result<String> getPDF1(@RequestParam("path") String path){
        return Result.ok("data:application/pdf;base64,"+previewResourceService.getPDF1(path));
    }


    @SneakyThrows
    @GetMapping("/pdf2")
    @Operation(summary = "资源文件转文件流")
    public void getPDF2(@RequestParam("path") String path,HttpServletResponse response){
        System.out.println(111);
        previewResourceService.getPDF2(path,response);
    }
}
