package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.service.JudgeService;
import net.edu.module.service.RecordService;
import net.edu.module.vo.JudgeRecordSubmitVO;
import net.edu.module.vo.LessonJudgeRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/4 1:02
 * @Version: 1.0
 * @Description:
 */
@RestController
@Tag(name = "代码题判题接口")
public class JudgeController {

    @Autowired
    JudgeService judgeService;
    @Autowired
    RecordService recordService;

    @PostMapping("/record")
    public Result<Integer> judge(@RequestBody JudgeRecordSubmitVO vo) {
        vo.setSubmitStatus(0);
        vo.setUserId(SecurityUser.getUserId());
        if (StringUtils.isEmpty(vo.getSubmitImg())) {
            vo.setSubmitImg(null);
        }
        judgeService.judgeBefore(vo);
        return Result.ok();
    }

    @PostMapping("/getRecord")
    public Result<JudgeRecordSubmitVO> getRecord(@RequestBody JudgeRecordSubmitVO vo) {
        return Result.ok(recordService.getRecord(vo));
    }


    //获取课堂中每个人每题的答题记录
    @GetMapping("/lesson/problem/record")
    public Result<List<LessonJudgeRecordVo>> getLessonProblemRecord(@RequestParam("lessonId") Long lessonId, @RequestParam(value = "type",required = false) Integer type){
        return Result.ok(recordService.getLessonProblemRecord(lessonId,type));
    }

}
