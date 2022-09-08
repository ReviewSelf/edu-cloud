package net.edu.module.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.api.EduFileApi;
import net.edu.module.vo.FileUploadVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/6 20:03
 * @Version: 1.0
 * @Description:
 */
@RestController
@Tag(name="新增模块演示")
@AllArgsConstructor
public class TestController {
    @Autowired
    private EduFileApi eduFileApi;

    @PostMapping("test")
    public Result<FileUploadVO> test(@RequestParam("file") MultipartFile file) {
        return Result.ok(eduFileApi.upload(file,"1000"));
    }


}
