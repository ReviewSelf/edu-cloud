package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.convert.ExamConvert;
import net.edu.module.entity.ExamEntity;
import net.edu.module.query.ExamUserExcelQuery;
import net.edu.module.service.ExamService;
import net.edu.module.query.ExamQuery;
import net.edu.module.vo.ExamAbilityVo;
import net.edu.module.vo.ExamVO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * 考试
 *
 * @author 小樊 babamu@126.com
 * @since 1.0.0 2022-10-09
 */
@RestController
@RequestMapping("/exam")
@Tag(name = "考试")
@AllArgsConstructor
public class ExamController {
    private final ExamService examService;


    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<ExamVO>> page(@Valid ExamQuery query) {
        PageResult<ExamVO> page = examService.page(query);

        return Result.ok(page);
    }

    @GetMapping("studentPage")
    @Operation(summary = "学生端分页")
    public Result<PageResult<ExamVO>> studentPage(@Valid ExamQuery query) {
        query.setUserId(SecurityUser.getUserId());
        PageResult<ExamVO> page = examService.studentPage(query);
        return Result.ok(page);
    }

    @GetMapping("Examing")
    @Operation(summary = "正在进行的考试")
    public Result<List<ExamVO>> list() {
        List<ExamVO> list = examService.getExamingList(SecurityUser.getUserId());
        return Result.ok(list);
    }


    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<ExamVO> get(@PathVariable("id") Long id) {
        ExamEntity entity = examService.getById(id);
        return Result.ok(ExamConvert.INSTANCE.convert(entity));
    }


    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody ExamVO vo) {

        vo.setTeacherId(SecurityUser.getUserId());

        examService.save(vo);
        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid ExamVO vo) {
        vo.setTeacherId(SecurityUser.getUserId());
        examService.update(vo);
        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList) {
        examService.delete(idList);
        return Result.ok();
    }

    @GetMapping("updateExamIndex/{examId}")
    @Operation(summary = "更新考试题目索引")
    public Result<String> updateExamIndex(@PathVariable Long examId) {
        examService.updateExamIndex(examId);
        return Result.ok();
    }

    @GetMapping("submitPaper/{examId}")
    @Operation(summary = "交卷，删除缓存，更新考试状态")
    public Result<String> submitPaper(@PathVariable Long examId) {
        Long userId = SecurityUser.getUserId();
        examService.submitPaper(examId, userId);
        return Result.ok();
    }

    @GetMapping("/exportExam")
    @Operation(summary = "导出总体考试情况excel")
    public void exportExam(@RequestParam(value = "examId") Long examId, HttpServletResponse response) throws IOException {
        examService.exportExam(examId, response);
    }

    @PostMapping("/exportUserExam")
    @Operation(summary = "导出单个或多个用户考试详情excel")
    public void exportUserExam(@RequestBody ExamUserExcelQuery query, HttpServletResponse response) throws IOException {
        examService.exportUserExam(query.getExamId(), query.getUserIdList(), response);
    }


    @GetMapping("/getAbilityExam")
    @Operation(summary = "获取能力考试列表以及当前用户参加的额能力考试id")
    public Result<ExamAbilityVo> getAbilityExam() {
        return Result.ok(examService.getAbilityExam());
    }

    @GetMapping("/promulgateGrade")
    @Operation(summary = "根据老师设定的及格分数给学生颁布等级")
    public Result<String> promulgateGrade(@RequestParam("examId") Long examId, @RequestParam("gainAbilityId") Long gainAbilityId, @RequestParam("score") Integer score) {
        examService.promulgateGrade(examId, gainAbilityId, score);
        return Result.ok();
    }

    @GetMapping("/downloadZip")
    @Operation(summary = "下载题目附件")
    public Result<String> downloadZip(HttpServletResponse response,
                                      @RequestParam("problemName") String problemName,
                                      @RequestParam("problemId") Long problemId,
                            @RequestParam("problemType") Integer problemType,
                            @RequestParam("source") Integer source,
                            @RequestParam("sourceId") Long sourceId) throws IOException {

        examService.downloadZip(response,problemName,problemId,problemType,source,sourceId);
        return Result.ok();


    }


}
