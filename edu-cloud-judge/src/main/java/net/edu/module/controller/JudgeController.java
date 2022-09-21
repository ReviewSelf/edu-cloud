package net.edu.module.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.service.JudgeService;
import net.edu.module.vo.JudgeRecordSubmitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
public class JudgeController {

    @Autowired
    JudgeService judgeService;

    @PostMapping("/record")
    public Result<Integer> judge(@RequestBody JudgeRecordSubmitVO vo){
        vo.setSubmitStatus(0);
        vo.setUserId(SecurityUser.getUserId());
        if(StringUtils.isEmpty(vo.getSubmitImg())){
            vo.setSubmitImg(null);
        }
        return Result.ok( judgeService.judgeBefore(vo));
        judgeService.judgeBefore(vo);
        return Result.ok();
    }

    @PostMapping("/getRecord")
    public Result<JudgeRecordSubmitVO> getRecord(@RequestBody JudgeRecordSubmitVO vo){
        return Result.ok(judgeService.getRecord(vo));
    }


}
