package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.service.StatisticsLessonInfoService;
import net.edu.module.service.StudentLessonService;
import net.edu.module.vo.StudentsStatisticsInfoVO;
import net.edu.module.vo.TeacherStatisticsInfoVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: sqw
 * @Date: 2022/11/24 9:33
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("statistics")
@AllArgsConstructor
public class StatisticsLessonInfoController {

    private final StatisticsLessonInfoService statisticsLessonInfoService;

    @GetMapping("/students")
    @Operation(summary = "获取学生统计结果信息")
    public Result<StudentsStatisticsInfoVO> getStudentsStatisticsInfo() {
        StudentsStatisticsInfoVO statisticsInfo = statisticsLessonInfoService.getStudentsStatisticsInfo(SecurityUser.getUserId());
        return Result.ok(statisticsInfo);
    }

    @GetMapping("/teacher")
    @Operation(summary = "获取学生统计结果信息")
    public Result<TeacherStatisticsInfoVO> getTeacherStatisticsInfo() {
        TeacherStatisticsInfoVO statisticsInfo = statisticsLessonInfoService.getTeacherStatisticsInfo(SecurityUser.getUserId());
        return Result.ok(statisticsInfo);
    }

}
