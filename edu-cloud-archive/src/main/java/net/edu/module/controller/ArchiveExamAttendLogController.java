package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.service.ArchiveExamAttendLogService;
import net.edu.module.service.ArchiveExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}