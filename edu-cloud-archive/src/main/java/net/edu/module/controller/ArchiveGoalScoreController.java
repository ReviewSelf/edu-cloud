package net.edu.module.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.service.ArchiveGoalScoreService;
import net.edu.module.vo.ArchiveGoalScoreInBooKVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
* 考试成绩表
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-16
*/
@RestController
@RequestMapping("goal/score")
@Tag(name="考试成绩表")
@AllArgsConstructor
public class ArchiveGoalScoreController {

    @Autowired
    private ArchiveGoalScoreService archiveGoalScoreService;
    @PostMapping("grade")
    @Operation(summary = "获取成绩表")
    public Result<ArchiveGoalScoreInBooKVO> getGradeInfo(@RequestBody JSONObject jsonObject){
        JSONObject classInfo=JSONUtil.parseObj(jsonObject.get("classInfo"))  ;
        String id= String.valueOf(jsonObject.get("id"));
        ArchiveGoalScoreInBooKVO archiveGoalScoreInBooKVO =archiveGoalScoreService.getGradeInfo(classInfo,id);
        return Result.ok(archiveGoalScoreInBooKVO);
    }

}
