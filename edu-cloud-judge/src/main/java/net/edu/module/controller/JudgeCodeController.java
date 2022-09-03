package net.edu.module.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.edu.framework.common.utils.Result;
import net.edu.module.entity.CodeRecordEntity;
import net.edu.module.service.JudgeCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/4 1:02
 * @Version: 1.0
 * @Description:
 */
@RestController
@Tag(name="代码题判题接口")
public class JudgeCodeController {

    @Autowired
    JudgeCodeService judgeCodeService;

    @PostMapping("/code")
    public Result<Integer> judge(@RequestBody CodeRecordEntity codeRecordEntity){

        return Result.ok( judgeCodeService.judgeBefore(codeRecordEntity));
    }
}
