package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.ExamAttendLogConvert;
import net.edu.module.entity.ExamAttendLogEntity;
import net.edu.module.query.ExamAttendLogQuery;
import net.edu.module.service.ExamAttendLogService;
import net.edu.module.vo.ExamAttendLogVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("exam/attend")
@Tag(name = "课程表")
@AllArgsConstructor
public class ExamAttendLogController {

    private final ExamAttendLogService examAttendLogService;

    @GetMapping("page")
    @Operation(summary = "分页")

    public Result<PageResult<ExamAttendLogVO>> page(@Valid ExamAttendLogQuery query){
        PageResult<ExamAttendLogVO> page = examAttendLogService.page(query);

        return Result.ok(page);
    }

    @GetMapping("list")
    @Operation(summary = "获取所有列表")

    public Result<List<ExamAttendLogVO>> getList(@RequestParam("examId") Long examId,@RequestParam("status") Integer status,@RequestParam("isCorrecting") Integer isCorrecting){
        List<ExamAttendLogVO> list = examAttendLogService.getList(examId,status,isCorrecting);
        return Result.ok(list);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<ExamAttendLogVO> get(@PathVariable("id") Long id){
        ExamAttendLogEntity entity = examAttendLogService.getById(id);

        return Result.ok(ExamAttendLogConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody ExamAttendLogVO vo){
        examAttendLogService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "更新分数")
    public Result<String> updateAttendLogScore(@RequestBody ExamAttendLogVO vo){
        examAttendLogService.updateAttendLogScore(vo);

        return Result.ok();
    }

    @GetMapping("user/{examId}")
    @Operation(summary = "教师点名获取课次中的学生")
    public Result<ExamAttendLogVO> studentsList(@PathVariable("examId") Long examId) {
        return Result.ok(examAttendLogService.getUserExamAttend(examId));
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        examAttendLogService.delete(idList);

        return Result.ok();
    }



    @GetMapping("invitation")
    public Result<String> genExamInvitationCode(@RequestParam("examId") Long examId,@RequestParam("code") String code,@RequestParam("time")Long time){
        examAttendLogService.genExamInvitationCode(examId,code,time);
        return Result.ok();
    }

    @GetMapping("receive")
    public Result<String> receiveExamInvitation(@RequestParam("code") String code){
        examAttendLogService.receiveExamInvitation(code);
        return Result.ok();
    }


    @GetMapping("getUserExamInfo")
    public Result<ExamAttendLogVO> getUserExamInfo(@RequestParam("userId") Long userId,@RequestParam("examId") Long examId){
        return Result.ok(examAttendLogService.getUserExamInfo(userId,examId));
    }



}
