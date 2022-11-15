package net.edu.mini.controller;

import io.swagger.v3.oas.annotations.Operation;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.user.SecurityUser;
import net.edu.mini.service.WxMiniService;
import net.edu.mini.vo.MyLessonVo;
import net.edu.mini.vo.MyMessage;
import net.edu.module.api.EduLessonApi;
import net.edu.module.api.EduProblemApi;
import net.edu.module.api.EduTeachApi;
import net.edu.module.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description: TODO
 * @author: sl
 * @date: 2022年10月31日 15:29
 */
@RestController
@RequestMapping("mini")
public class WxMiniController {

    @Autowired
    private EduLessonApi eduLessonApi;

    @Autowired
    private EduProblemApi eduProblemApi;
    @Autowired
    private EduTeachApi eduTeachApi;
    @Autowired
    private WxMiniService wxMiniService;
    @GetMapping("/homeWorkPage")
    public Result<PageResult<HomeWorkVO>> getStudentHomeWorkPage(@RequestParam(value = "limit") Integer limit, @RequestParam(value = "page") Integer page){
        Long userId=SecurityUser.getUserId();
        return eduLessonApi.getStudentHomeWorkPage(userId,limit,page);
    }



    @GetMapping("/choice/{problemId}")
    public Result<ChoiceProblemVO> getChoiceProblemInfo(@PathVariable("problemId") Long problemId) {
        return eduProblemApi.getChoiceProblemInfo(problemId);
    }

    @GetMapping("/code/{problemId}")
    public Result<CodeProblemVO> getCodeProblemInfo(@PathVariable("problemId") Long problemId) {
        return eduProblemApi.getCodeProblemInfo(problemId);
    }

    @GetMapping("/fill/{problemId}")
    public Result<FillProblemVO> getFillProblemInfo(@PathVariable("problemId")  Long problemId){
        return eduProblemApi.getFillProblemInfo(problemId);
    }

    @GetMapping("/myLesson")
    public Result<List<MyLessonVo>> getMyLesson(@RequestParam String time, String userId) {
        List<MyLessonVo> list = wxMiniService.getLesson(time , userId);
        return Result.ok(list);
    }

    @GetMapping("/getMessage")
    public Result<MyMessage> getMessage(@RequestParam String userId) {
        MyMessage user = wxMiniService.getMessage(userId);
        return Result.ok(user);
    }


    @GetMapping("/enrollClass/page")
    @Operation(summary = "分页")
    public Result<PageResult<EnrollClassVO>> page(@RequestParam(value = "limit") Integer limit,
                                                  @RequestParam(value = "page") Integer page,
                                                  @RequestParam(value = "className")String className){
        return eduTeachApi.page(limit,page,className);
    }

    @GetMapping("/exam/studentPage")
    @Operation(summary = "学生端分页")
    public Result<PageResult<ExamVO>> studentPage(@RequestParam(value = "limit") Integer limit,
                                                  @RequestParam(value = "page") Integer page,
                                                  @RequestParam(value="beginTime")String beginTime,
                                                  @RequestParam(value="endTime")String endTime,
                                                  @RequestParam(value = "status")String status){
        Long userId=SecurityUser.getUserId();
        return eduLessonApi.studentPage(limit,page,userId,beginTime,endTime,status);
    }


    @GetMapping("/exam/problem/list/{examId}")
    @Operation(summary = "获取考试题目信息")
    public Result<List<ExamProblemEntity>> list(@PathVariable("examId") Long examId){

        return eduLessonApi.getExamList(examId);
    }

}
