package net.edu.mini.controller;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.api.EduLessonApi;
import net.edu.module.api.EduTeachApi;
import net.edu.module.vo.HomeWorkQuery;
import net.edu.module.vo.HomeWorkVO;
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

    @PostMapping("/homeWorkPage")
    public Result<PageResult<HomeWorkVO>> getStudentHomeWorkPage(@RequestBody HomeWorkQuery query){

        System.out.println(query);
        System.out.println(eduLessonApi.getStudentHomeWorkPage(query));
        return Result.ok(eduLessonApi.getStudentHomeWorkPage(query).getData());
    }

}
