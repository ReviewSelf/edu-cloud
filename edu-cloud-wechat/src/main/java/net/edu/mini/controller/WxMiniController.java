package net.edu.mini.controller;

import io.lettuce.core.dynamic.annotation.Param;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.mini.service.WxMiniService;
import net.edu.mini.vo.MyLessonVo;
import net.edu.mini.vo.MyMessage;
import net.edu.module.api.EduLessonApi;
import net.edu.module.api.EduTeachApi;
import net.edu.module.vo.HomeWorkQuery;
import net.edu.module.vo.HomeWorkVO;
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
    private WxMiniService wxMiniService;

    @PostMapping("/homeWorkPage")
    public Result<PageResult<HomeWorkVO>> getStudentHomeWorkPage(@RequestBody HomeWorkQuery query){

        System.out.println(query);
        System.out.println(eduLessonApi.getStudentHomeWorkPage(query));
        return Result.ok(eduLessonApi.getStudentHomeWorkPage(query).getData());
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

}
