package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.service.LocalStorageService;
import net.edu.module.service.StorageService;
import net.edu.module.vo.FileUploadVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 */
@RestController
@Tag(name = "文件上传")
@AllArgsConstructor
public class FileUploadController {
    private final StorageService storageService=new LocalStorageService();


    @PostMapping("upload")
    @Operation(summary = "普通文件上传,对外公开文件")
    public Result<FileUploadVO> upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return Result.error("请选择需要上传的文件");
        }
        // 上传路径
        String path = storageService.getPath(file.getOriginalFilename());
        // 上传文件
        String url = storageService.upload(file.getBytes(), path);
        FileUploadVO vo = new FileUploadVO();
        vo.setUrl(url);
        vo.setSize(file.getSize());
        vo.setName(file.getOriginalFilename());
        return Result.ok(vo);
    }

    @PostMapping("upload2")
    @Operation(summary = "不对外公开文件")
    public Result<FileUploadVO> upload2(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return Result.error("请选择需要上传的文件");
        }
        // 上传文件
        String url = storageService.upload2(file, file.getOriginalFilename());
        FileUploadVO vo = new FileUploadVO();
        vo.setUrl(url);
        vo.setSize(file.getSize());
        vo.setName(file.getOriginalFilename());
        return Result.ok(vo);
    }
//    @PostMapping("upload")
//    @Operation(summary = "普通文件上传")
//    public Result<FileUploadVO> upload(@RequestParam("file") MultipartFile file) throws Exception {
//        if (file.isEmpty()) {
//            return Result.error("请选择需要上传的文件");
//        }
//        // 上传路径
//        String path = storageService.getPath(file.getOriginalFilename());
//        // 上传文件
//        String url = storageService.upload(file.getBytes(), path);
//        FileUploadVO vo = new FileUploadVO();
//        vo.setUrl(url);
//        vo.setSize(file.getSize());
//        vo.setName(file.getOriginalFilename());
//        return Result.ok(vo);
//    }


}
