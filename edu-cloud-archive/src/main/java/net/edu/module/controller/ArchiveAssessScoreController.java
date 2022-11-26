package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.entity.ArchiveAssessScoreEntity;
import net.edu.module.service.ArchiveAssessScoreService;
import net.edu.module.service.ArchiveTestScoreService;
import net.edu.module.vo.ArchiveAssessScoreVO;
import net.edu.module.vo.ArchiveTestScoreVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* 考试成绩表
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-16
*/
@RestController
@RequestMapping("assess/score")
@Tag(name="考试成绩表")
@AllArgsConstructor
public class ArchiveAssessScoreController {

    private final ArchiveAssessScoreService archiveAssessScoreService;


    @GetMapping("{courseId}")
    @Operation(summary = "信息")
    public Result<List<ArchiveAssessScoreVO>> selectTestScoreByCourseId(@PathVariable("courseId") Long courseId){
        List<ArchiveAssessScoreVO> vo = archiveAssessScoreService.selectAssessScoreByCourseId(courseId);
        return Result.ok(vo);
    }



}
