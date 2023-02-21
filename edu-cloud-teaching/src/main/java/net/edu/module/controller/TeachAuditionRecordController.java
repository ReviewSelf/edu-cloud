package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.TeachAuditionRecordConvert;
import net.edu.module.entity.TeachAuditionRecordEntity;
import net.edu.module.query.TeachAuditionRecordQuery;
import net.edu.module.service.TeachAuditionRecordService;
import net.edu.module.vo.TeachAuditionRecordVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 教学试听安排
*
* @author sqw 
* @since 1.0.0 2023-02-13
*/
@RestController
@RequestMapping("audition/record")
@Tag(name="教学试听安排")
@AllArgsConstructor
public class TeachAuditionRecordController {
    private final TeachAuditionRecordService teachAuditionRecordService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<TeachAuditionRecordVO>> page(@Valid TeachAuditionRecordQuery query){
        PageResult<TeachAuditionRecordVO> page = teachAuditionRecordService.page(query);

        return Result.ok(page);
    }

    @GetMapping("page/newStudent")
    @Operation(summary = "分页")
    public Result<PageResult<TeachAuditionRecordVO>> newStudentAuditionRecordPage(@Valid TeachAuditionRecordQuery query){
        PageResult<TeachAuditionRecordVO> page = teachAuditionRecordService.newStudentAuditionRecordPage(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<TeachAuditionRecordVO> get(@PathVariable("id") Long id){
        TeachAuditionRecordEntity entity = teachAuditionRecordService.getById(id);

        return Result.ok(TeachAuditionRecordConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody TeachAuditionRecordVO vo){
        teachAuditionRecordService.save(vo);

        return Result.ok();
    }

//    @PostMapping("notLesson")
//    @Operation(summary = "保存未安排试听的记录")
//    public Result<String> saveNotLesson(@RequestBody TeachAuditionRecordVO vo){
//        teachAuditionRecordService.saveNotLesson(vo);
//
//        return Result.ok();
//    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid TeachAuditionRecordVO vo){
        teachAuditionRecordService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        teachAuditionRecordService.delete(idList);

        return Result.ok();
    }

    @PostMapping("joinAudition")
    @Operation(summary = "加入试听课")
    public Result<String> joinAuditionLesson(@RequestBody TeachAuditionRecordVO vo){
        teachAuditionRecordService.joinAuditionLesson(vo);

        return Result.ok();
    }

    @GetMapping("/detail/{id}")
    @Operation(summary = "信息")
    public Result<TeachAuditionRecordVO> getDetail(@PathVariable("id") Long id){
        TeachAuditionRecordVO vo = teachAuditionRecordService.getAuditionRecord(id);

        return Result.ok(vo);
    }
}