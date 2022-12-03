package net.edu.module.controller;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.convert.ArchiveCourseSummaryConvert;
import net.edu.module.entity.ArchiveCourseSummaryEntity;
import net.edu.module.service.ArchiveCourseSummaryService;
import net.edu.module.query.ArchiveCourseSummaryQuery;
import net.edu.module.vo.ArchiveAssessTableVo;
import net.edu.module.vo.ArchiveAssessTestGradesVo;
import net.edu.module.vo.ArchiveCourseSummaryVO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
* 课程总结
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-14
*/
@RestController
@RequestMapping("summary")
@Tag(name="课程总结")
@AllArgsConstructor
public class ArchiveCourseSummaryController {
    private final ArchiveCourseSummaryService archiveCourseSummaryService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<ArchiveCourseSummaryVO>> page(@Valid ArchiveCourseSummaryQuery query){

        PageResult<ArchiveCourseSummaryVO> page = archiveCourseSummaryService.page(query);
        System.out.println(SecurityUser.getUser().getRealName());
        System.out.println(page);
        return Result.ok(page);

    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<ArchiveCourseSummaryVO> get(@PathVariable("id") Long id){
        ArchiveCourseSummaryEntity entity = archiveCourseSummaryService.getById(id);

        return Result.ok(ArchiveCourseSummaryConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody ArchiveCourseSummaryVO vo){
        archiveCourseSummaryService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid ArchiveCourseSummaryVO vo){
        archiveCourseSummaryService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        archiveCourseSummaryService.delete(idList);

        return Result.ok();
    }

    @PostMapping("improve")
    @Operation(summary = "插入问题和改进措施(第六步)")
    public Result<String> insertMeasures(@RequestBody ArchiveCourseSummaryVO vo){
        archiveCourseSummaryService.insertMeasures(vo);
        return Result.ok();
    }

    @PostMapping("analysis")
    @Operation(summary = "插入分析说明(第七步)")
    public Result<String> insertAnalysis(@RequestBody ArchiveCourseSummaryVO vo){
        archiveCourseSummaryService.insertAnalysis(vo);
        return Result.ok();
    }

    @PostMapping("final")
    @Operation(summary = "完成(第八步)")
    public Result<String> insertFinal(@RequestBody ArchiveCourseSummaryVO vo){
        archiveCourseSummaryService.insertFinal(vo);
        return Result.ok();
    }

    @PostMapping("exportExcelSummary")
    @Operation(summary = "导出课程总体情况excel表")
    public void exportExcelSummary(@RequestBody JSONObject object, HttpServletResponse response) throws IOException {
        Long courseId= Long.valueOf(object.get("courseId").toString());
        Long summaryId= Long.valueOf(object.get("summaryId").toString());
        archiveCourseSummaryService.exportExcelSummary(courseId,summaryId,response);
    }

    @PostMapping("/StepOneNext")
    @Operation(summary = "新增课程总结表记录")
    public Result<Long> creativeSummaryId(@RequestBody ArchiveCourseSummaryVO summaryVO) {
        Long summaryId = archiveCourseSummaryService.creativeSummaryId(summaryVO);
        return Result.ok(summaryId);
    }

    @GetMapping("/getGradesTable")
    @Operation(summary = "获取成绩录入表")
    public Result<List<ArchiveAssessTestGradesVo>> getGradesTable(@RequestParam String courseId , String summaryId) {
        archiveCourseSummaryService.getGradesTable(courseId , summaryId);
        List<ArchiveAssessTestGradesVo> list = archiveCourseSummaryService.selectArchiveStep3(courseId , summaryId);
        return Result.ok(list);
    }

    @GetMapping("/getScoreEveRage")
    @Operation(summary = "考核点平均得分")
    public Result<List<Integer>> getScoreEvaRage(@RequestParam String courseId , String summaryId) {
        List<Integer> list = archiveCourseSummaryService.getScoreEveRage(courseId , summaryId);
        return Result.ok(list);
    }
    @PostMapping("exportTeachingWord")
    @Operation(summary = "导出教学日历表")
    public void exportTeachingWord(@RequestBody JSONObject object, HttpServletResponse response) throws IOException {
        System.out.println(object);
        Long courseId= Long.valueOf(object.get("courseId").toString());
        Long summaryId= Long.valueOf(object.get("summaryId").toString());
        archiveCourseSummaryService.createTeachingWord(courseId,summaryId,response);
    }

    @GetMapping("/getMannerPq")
    @Operation(summary = "获取考核方式总占比")
    public Result<List<BigDecimal>> getMannerPq(@RequestParam String courseId) {
        List<BigDecimal> list = archiveCourseSummaryService.selectMannerPq(courseId);
        System.out.println(list);
        return Result.ok(list);
    }

    @GetMapping("/getPeaceData")
    @Operation(summary = "获取第二步平时考核方式下教学目标占比")
    public Result<List<String>> getPeaceData(@RequestParam Integer courseId) {
        List<String> list = archiveCourseSummaryService.selectPeaceData(courseId);
        return Result.ok(list);
    }
}
