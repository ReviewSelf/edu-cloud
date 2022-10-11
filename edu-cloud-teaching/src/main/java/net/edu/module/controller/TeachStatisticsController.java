package net.edu.module.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.entity.TeachStatisticsInfoEntity;
import net.edu.module.service.TeachStatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("statistics")
@AllArgsConstructor
@Tag(name="统计模块")
public class TeachStatisticsController {
    private final TeachStatisticsService statisticsService;

    @GetMapping("")
    @Operation(summary = "获取统计结果信息")
    public Result<TeachStatisticsInfoEntity> getStatisticsInfo() {
        TeachStatisticsInfoEntity statisticsInfo = statisticsService.getStatisticsInfo();
        return Result.ok(statisticsInfo);
    }


    @GetMapping("/info")
    @Operation(summary = "统计信息用于定时器")
    public Result<String> statisticsHomeInfo() {
        statisticsService.statisticsHomeInfo();
        return Result.ok();
    }
}
