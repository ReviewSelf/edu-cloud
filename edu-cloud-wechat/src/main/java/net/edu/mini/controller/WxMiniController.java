package net.edu.mini.controller;

import io.swagger.v3.oas.annotations.Operation;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.api.EduLessonApi;
import net.edu.module.api.EduProblemApi;
import net.edu.module.api.EduTeachApi;
import net.edu.module.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/homeWorkPage")
    public Result<PageResult<HomeWorkVO>> getStudentHomeWorkPage(@Valid HomeWorkQuery query){

        return eduLessonApi.getStudentHomeWorkPage(query.getStudentId(),query.getLimit(),query.getPage());
    }

    @GetMapping("/studentPage")
    public Result<PageResult<ExamVO>> studentPage(@RequestParam(value = "limit")Integer limit,@RequestParam(value="page")Integer page,@RequestParam(value="beginTime")String beginTime,@RequestParam(value="endTime")String endTime){
        Long userId=SecurityUser.getUserId();
        return eduLessonApi.studentPage(limit, page,userId,beginTime,endTime);
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

}
