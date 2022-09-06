package net.edu.module.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.api.EduFileApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("test")
    public Result<String> test() {

        return Result.ok(eduFileApi.upload(null));
    }
}
