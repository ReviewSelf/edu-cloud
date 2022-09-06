package net.edu.module.controller;

import cn.hutool.core.io.file.FileNameUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.edu.framework.common.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 */
@RestController
@RequestMapping("/sample")
@Tag(name = "文件上传")
public class SampleUploadController {

    @PostMapping("/upload")
    @Operation(summary = "上传一组测试用例")
    public String upload(){
    //  public String upload( @RequestParam("file") MultipartFile[] files) {
        //in和out文件以及problemId

//        for (MultipartFile file :files){
//            // 主文件名，不包含扩展名
//            String prefix = FileNameUtil.getPrefix(file.getOriginalFilename());
//            // 文件扩展名
//            String suffix = FileNameUtil.getSuffix(file.getOriginalFilename());
//            if("in".equals(suffix)){
//                //输入
//
//            }
//            else if("out".equals(suffix)){
//
//            }
//            else {
//                throw new RuntimeException("只能上传in和out格式文件");
//            }
//
//        }
        return "ok";


    }

    @PostMapping("/uploadBatch")
    @Operation(summary = "多个测试样例上传")
    public Result<String> uploadBatch() throws Exception {
        //传入一个问题的多组测试样例（输入+输出）
        //传入一个大文件夹，里面有子文件夹，每个子文件夹里有in和out文件   以及problemId

        return Result.ok();
    }

}
