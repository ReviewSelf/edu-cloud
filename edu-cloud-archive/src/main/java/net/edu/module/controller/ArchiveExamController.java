package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.service.ArchiveExamService;
import net.edu.module.vo.ArchiveTargetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author weng
 * @date 2022/11/10 - 16:26
 **/
@RestController
@RequestMapping("exam")
@Tag(name="考试")
@AllArgsConstructor
public class ArchiveExamController {

    @Autowired
    private ArchiveExamService archiveExamService;

    @GetMapping("insertExam")
    @Operation(summary = "数据迁移")
    public Result<Integer> insertExam(){
        System.out.println("111");
        return Result.ok(archiveExamService.insertExam());
    }
}
