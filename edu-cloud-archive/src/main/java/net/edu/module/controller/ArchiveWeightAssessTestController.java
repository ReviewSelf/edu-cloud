package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.service.ArchiveWeightAssessTestService;
import net.edu.module.vo.ArchiveCourseVO;
import net.edu.module.vo.ArchiveWeightAssessTestVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author weng
 * @date 2022/11/3 - 11:44
 **/
@RestController
@RequestMapping("weightTest")
@Tag(name="评测点权重")
@AllArgsConstructor
public class ArchiveWeightAssessTestController {

    private final ArchiveWeightAssessTestService archiveWeightAssessTestService;

    @PostMapping("test")
    @Operation(summary = "插入评测点权重")
    public Result<Integer> insertTestWeight(@RequestBody List<ArchiveWeightAssessTestVO> vos){
        return Result.ok(archiveWeightAssessTestService.insertTestWeight(vos));
    }

    @GetMapping("assess")
    @Operation(summary = "获取评测点")
    public Result<List<ArchiveWeightAssessTestVO>> getAssessTest(@RequestParam("assessId") Long assessId){
        return Result.ok(archiveWeightAssessTestService.selectAssessTest(assessId));
    }

    @DeleteMapping("")
    @Operation(summary = "删除")
    public Result<String> delete(@RequestParam("testId")Long testId){
        archiveWeightAssessTestService.delete(testId);
        return Result.ok();
    }

    @GetMapping("testAll")
    @Operation(summary = "课程信息")
    public Result<List<ArchiveWeightAssessTestVO>> selectArchiveTestAll(@RequestParam("assessId") Long assessId){
        return Result.ok(archiveWeightAssessTestService.selectArchiveTestAll(assessId));
    }
}
