package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.GraduateRequireConvert;
import net.edu.module.entity.GraduateRequireEntity;
import net.edu.module.query.GraduateRequireQuery;
import net.edu.module.service.ArchiveTargetService;
import net.edu.module.service.GraduateRequireService;
import net.edu.module.vo.GraduateRequireVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
* 毕业要求
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-20
*/
@RestController
@RequestMapping("graduateRequire")
@Tag(name="毕业要求")
@AllArgsConstructor
public class GraduateRequireController {
    private final GraduateRequireService graduateRequireService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<GraduateRequireVO>> page(@Valid GraduateRequireQuery query){
        System.out.println(query);
        PageResult<GraduateRequireVO> page = graduateRequireService.page(query);
        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<GraduateRequireVO> get(@PathVariable("id") Long id){
        GraduateRequireEntity entity = graduateRequireService.getById(id);
        return Result.ok(GraduateRequireConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody GraduateRequireVO vo){
        graduateRequireService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid GraduateRequireVO vo){
        graduateRequireService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        graduateRequireService.delete(idList);

        return Result.ok();
    }

    @GetMapping("graduate")
    public Result<List<GraduateRequireEntity>> selectGraduateRequireList(@RequestParam("grade")String grade){
        return Result.ok(graduateRequireService.selectGraduateByGrade(grade));
    }

    @PostMapping("/import")
    public Result<String> studentFromExcel(@RequestParam("file") MultipartFile file) {
        graduateRequireService.importArchive(file);
        return Result.ok();
    }

    @PostMapping("batch")
    @Operation(summary = "年度毕业要求生成")
    public Result<String> saveBatchRequire(@RequestBody GraduateRequireVO vo){
        graduateRequireService.saveBatchRequire(vo);

        return Result.ok();
    }


    /**
     * 根据毕业要求id获取毕业要求对应的课程权重。暂不用
     * @param id
     * @return
     */
//    @GetMapping("weight")
//    @Operation(summary = "权重")
//    public Result<List<GraduateRequireVO>> getWeight(@RequestParam("id")Long id){
//        return Result.ok(graduateRequireService.selectWeight(id));
//    }
}
