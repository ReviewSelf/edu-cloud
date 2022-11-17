package net.edu.module.controller;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.dao.ArchiveExamAttendLogDao;
import net.edu.module.service.ArchiveExamAttendLogService;
import net.edu.module.service.ArchiveExamService;
import net.edu.module.vo.ArchiveExamAttendLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author weng
 * @date 2022/11/10 - 16:26
 **/
@RestController
@RequestMapping("attend")
@Tag(name="考试")
@AllArgsConstructor
public class ArchiveExamAttendLogController {

    @Autowired
    private ArchiveExamAttendLogService archiveExamAttendLogService;

    @PostMapping
    @Operation(summary = "插入")
    public Result<Integer> insertExam(){
        return Result.ok(archiveExamAttendLogService.insertExamAttendLog());
    }

    @GetMapping("{examId}")
    @Operation(summary = "信息")
    public Result<List<ArchiveExamAttendLogVO>> selectExamAttendLogByExamId(@PathVariable("examId") Long examId){
        return Result.ok(archiveExamAttendLogService.selectExamAttendLogByExamId(examId));
    }

    @PostMapping("exportExam")
    @Operation(summary = "导出总体考试情况excel")
    public void exportExam(@RequestBody JSONObject object, HttpServletResponse response) throws IOException {
        String a = object.getStr("examId");
        String b = object.getStr("classId");
        String[] examId = a.substring(1, a.length() - 1).split(",");
        String[] classId = b.substring(1, b.length() - 1).split(",");
        archiveExamAttendLogService.exportExam(examId, classId,response);
    }
}