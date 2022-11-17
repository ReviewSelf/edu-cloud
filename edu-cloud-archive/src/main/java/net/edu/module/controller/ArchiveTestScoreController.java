package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.ArchiveTestScoreConvert;
import net.edu.module.entity.ArchiveTestScoreEntity;
import net.edu.module.service.ArchiveTestScoreService;
import net.edu.module.query.ArchiveTestScoreQuery;
import net.edu.module.vo.ArchiveTestScoreVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
* 考试成绩表
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-16
*/
@RestController
@RequestMapping("score")
@Tag(name="考试成绩表")
@AllArgsConstructor
public class ArchiveTestScoreController {
    private final ArchiveTestScoreService archiveTestScoreService;



    @GetMapping("{courseId}")
    @Operation(summary = "信息")
    public Result<List<ArchiveTestScoreVO>> selectTestScoreByCourseId(@PathVariable("courseId") Long courseId){
        List<ArchiveTestScoreVO> vo = archiveTestScoreService.selectTestScoreByCourseId(courseId);
        return Result.ok(vo);
    }

    @PostMapping("/import")
    public Result<String> scoreImportExcel(@RequestParam("courseId") String courseId,@RequestParam("file") MultipartFile file) {
        System.out.println(courseId);
        archiveTestScoreService.scoreImportExcel(file, Long.valueOf(courseId));
        return Result.ok();
    }

}
