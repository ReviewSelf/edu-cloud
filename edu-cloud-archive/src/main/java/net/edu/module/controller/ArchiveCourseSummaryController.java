package net.edu.module.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.ArchiveCourseSummaryConvert;
import net.edu.module.entity.ArchiveCourseSummaryEntity;
import net.edu.module.service.ArchiveCourseSummaryService;
import net.edu.module.query.ArchiveCourseSummaryQuery;
import net.edu.module.vo.ArchiveCourseSummaryVO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
* 课程总结
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-14
*/
@RestController
@RequestMapping("summary")
@Tag(name="课程总结")
@AllArgsConstructor
public class ArchiveCourseSummaryController {
    private final ArchiveCourseSummaryService archiveCourseSummaryService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<ArchiveCourseSummaryVO>> page(@Valid ArchiveCourseSummaryQuery query){
        PageResult<ArchiveCourseSummaryVO> page = archiveCourseSummaryService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<ArchiveCourseSummaryVO> get(@PathVariable("id") Long id){
        ArchiveCourseSummaryEntity entity = archiveCourseSummaryService.getById(id);

        return Result.ok(ArchiveCourseSummaryConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody ArchiveCourseSummaryVO vo){
        archiveCourseSummaryService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid ArchiveCourseSummaryVO vo){
        archiveCourseSummaryService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        archiveCourseSummaryService.delete(idList);
        return Result.ok();
    }

    @PostMapping("exportExcelSummary")
    @Operation(summary = "导出课程总体情况excel表")
    public void exportExcelSummary(@RequestBody JSONObject object,HttpServletResponse response) throws IOException {
        System.out.println(object);
        archiveCourseSummaryService.exportExcelSummary(response);
    }

}
