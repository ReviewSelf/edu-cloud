package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.query.LessonChatLogQuery;
import net.edu.module.service.LessonChatLogService;
import net.edu.module.vo.LessonChatLogVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 马佳浩
 * @date 2022/12/9 09:19:43
 * @description
 */
@RestController
@AllArgsConstructor
@RequestMapping("chat")
public class LessonChatLogController {

    private final LessonChatLogService lessonChatLogService;

    @GetMapping("page")
    @Operation(summary = "消息记录")
    public Result<List<LessonChatLogVO>> page(@Valid LessonChatLogQuery query) {
        return Result.ok(lessonChatLogService.page(query));
    }


}
