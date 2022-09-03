package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.edu.framework.common.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/sample")
@Tag(name = "文件上传")
public class SampleUploadController {

    @PostMapping("/upload")
    @Operation(summary = "单个测试样例上传")
    public Result<String> upload() throws Exception {
        //传入一个问题的测试样例（输入+输出）
        //in和out文件以及problemId

        return Result.ok();
    }

    @PostMapping("/uploadBatch")
    @Operation(summary = "多个测试样例上传")
    public Result<String> uploadBatch() throws Exception {
        //传入一个问题的多组测试样例（输入+输出）
        //传入一个大文件夹，里面有子文件夹，每个子文件夹里有in和out文件   以及problemId

        return Result.ok();
    }

}
