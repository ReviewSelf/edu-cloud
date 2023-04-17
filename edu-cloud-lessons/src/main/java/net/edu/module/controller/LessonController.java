package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.LessonConvert;
import net.edu.module.entity.LessonEntity;
import net.edu.module.service.LessonService;
import net.edu.module.query.LessonQuery;
import net.edu.module.vo.LessonVO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
* 课程表
*
* @author 马佳浩
* @since 1.0.0 2022-09-15
*/
@RestController
@RequestMapping("lesson")
@Tag(name="课程表")
@AllArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @GetMapping("list")
    @Operation(summary = "课程列表")
    public Result<List<LessonVO>> list(@Valid LessonQuery query){
        return Result.ok(lessonService.list(query));
    }

    @GetMapping("page/info")
    @Operation(summary = "课程列表(无需验证身份)")
    public Result<PageResult<LessonVO>> selectLessonPage(@Valid LessonQuery query){
        return Result.ok(lessonService.selectLessonPage(query));
    }


    @GetMapping("page")
    @Operation(summary = "课程列表(验证身份)")
    public Result<PageResult<LessonVO>> page(@Valid LessonQuery query) {
        return Result.ok(lessonService.page(query));
    }

    @GetMapping("page/allLesson")
    @Operation(summary = "所有课程列表")
    public Result<PageResult<LessonVO>> pageAllLesson(@Valid LessonQuery query) {
        return Result.ok(lessonService.pageAllLesson(query));
    }

    @GetMapping("page/afootLesson")
    @Operation(summary = "所有进行中的课程列表")
    public Result<PageResult<LessonVO>> pageAfootLesson(@Valid LessonQuery query) {
        return Result.ok(lessonService.pageAfootLesson(query));
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<LessonVO> get(@PathVariable("id") Long id){
        LessonVO vo = lessonService.getLessonById(id);

        return Result.ok(vo);
    }

    @PostMapping("/create")
    @Operation(summary = "创建课堂")
    public Result<String> createLessons(@RequestBody List<LessonVO> voList){
        lessonService.createLessons(voList);
        return Result.ok();
    }

    @PostMapping("/create/audition")
    @Operation(summary = "创建试听课堂")
    public Result<String> createAuditionLessons(@RequestBody LessonVO vo){
        lessonService.createAuditionLessons(vo);
        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid LessonVO vo){
        lessonService.update(vo);

        return Result.ok();
    }

    @GetMapping("list/notStart/{classId}")
    @Operation(summary = "通过班级ID,查找未开始课表")
    public Result<List<LessonVO>> getClassNotStartLesson(@PathVariable("classId") Long classId) {
        List<LessonVO> list = lessonService.getClassNotStartLesson(classId);
        return Result.ok(list);
    }

    @GetMapping("list/getClassAllLesson/{classId}")
    @Operation(summary = "通过班级ID,查找所有课表")
    public Result<List<LessonVO>> getClassAllLesson(@PathVariable("classId") Long classId) {
        List<LessonVO> list = lessonService.getClassAllLesson(classId);
        return Result.ok(list);
    }

    @GetMapping("homework/page")
    @Operation(summary = "课程列表")
    public Result<PageResult<LessonVO>> homeworkPage(@Valid LessonQuery query) {
        return Result.ok(lessonService.homeworkPage(query));
    }

    @GetMapping("homeworkForStudentId/page")
    @Operation(summary = "课程列表根据学生id")
    public Result<PageResult<LessonVO>> homeworkForStudentIdPage(@Valid LessonQuery query) {
        return Result.ok(lessonService.homeworkForStudentIdPage(query));
    }

    @GetMapping("/homework/deadline")
    @Operation(summary = "回家作业超时截止操作")
    public Result<String> homeWorkDeadline(){
        lessonService.homeWorkDeadline();
        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody Long classId){
        lessonService.delete(classId);
        return Result.ok();
    }

    @PostMapping("updateList")
    @Operation(summary = "批量修改")
    public Result<String> updateList(@RequestBody List<LessonVO> list){
        lessonService.updateList(list);
        return Result.ok();
    }


    @GetMapping("/exportLesson")
    @Operation(summary = "导出总体考试情况excel")
    public void exportLesson(@RequestParam(value = "lessonId") Long lessonId, HttpServletResponse response) throws IOException {
        lessonService.exportLesson(lessonId,response);
    }


    @PutMapping("/homework")
    @Operation(summary = "回家作业发布、截止、修改")
    public Result<String> setHomework(@RequestBody @Valid LessonVO vo) {
        lessonService.updateHomework(vo);
        return Result.ok();
    }

    @GetMapping("historyHomework/page")
    @Operation(summary = "课程列表")
    public Result<PageResult<LessonVO>> historyHomeworkPage(@Valid LessonQuery query) {
        return Result.ok(lessonService.historyHomeworkPage(query));
    }

    @GetMapping("getLessonHour/{id}")
    @Operation(summary = "根据课程id获取课时")
    public Result<Double> getLessonHour(@PathVariable("id") Long id){
        System.out.println("123123123123");
        double res = lessonService.getLessonHour(id);
        return Result.ok(res);
    }

}
