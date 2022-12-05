package net.edu.module.controller;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.ArchiveScoreBookConvert;
import net.edu.module.query.ArchiveScoreBookQuery;
import net.edu.module.service.ArchiveAssessService;
import net.edu.module.service.ArchiveScoreBookService;
import net.edu.module.vo.*;
import net.maku.entity.ArchiveScoreBookEntity;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
* 记分册
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-22
*/
@RestController
@RequestMapping("archiveScoreBook")
@Tag(name="记分册")
@AllArgsConstructor
public class ArchiveScoreBookController {
    private final ArchiveScoreBookService archiveScoreBookService;


    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<ArchiveScoreBookVO>> page(@Valid ArchiveScoreBookQuery query){
        PageResult<ArchiveScoreBookVO> page = archiveScoreBookService.page(query);
        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<ArchiveScoreBookVO> get(@PathVariable("id") Long id){
        ArchiveScoreBookEntity entity = archiveScoreBookService.getById(id);

        return Result.ok(ArchiveScoreBookConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody ArchiveScoreBookVO vo){
        archiveScoreBookService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid ArchiveScoreBookVO vo){
        archiveScoreBookService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        archiveScoreBookService.delete(idList);

        return Result.ok();
    }

    @PostMapping("InsertClassInfo")
    @Operation(summary = "生成课程信息")
    public Result<String> InsertClassInfo(@RequestBody ArchiveScoreBookClassInfoVO vo){

        archiveScoreBookService.InsertClassInfo(vo);

        return Result.ok(String.valueOf(vo.getId()));
    }



    @GetMapping("getClassTable")
    @Operation(summary = "课程表")
    public Result<List<ArchiveScoreBookClassTableVO>> getClassTable(@RequestParam("id")String id){
        List<ArchiveScoreBookClassTableVO> archiveScoreBookClassTableVOList=archiveScoreBookService.getClassTable(id);
        return Result.ok(archiveScoreBookClassTableVOList);
    }

    @GetMapping("deleteClassTable")
    @Operation(summary = "删除课程表")
    public Result<List<ArchiveScoreBookClassTableVO>> deleteClassTable(@RequestParam("id")String id,@RequestParam("deleteId")String deleteId){
        archiveScoreBookService.deleteClassTable(id,deleteId);
        return Result.ok();
    }


    @PostMapping("updateClassTable")
    @Operation(summary = "课程表添加及修改")
    public Result<String> addClassTable(@RequestBody JSONObject jsonObject ){

        archiveScoreBookService.updateClassTable(String.valueOf(jsonObject.get("id")),jsonObject.get("dataForm"));
        return Result.ok();
    }

    @PostMapping("getScoreListInBook")
    @Operation(summary = "获取成绩表")
    public Result<List<ArchiveScoreInBookVO>> getScoreListInBook(@RequestBody JSONObject jsonObject){
        JSONObject classInfo=JSONUtil.parseObj(jsonObject.get("classInfo"))  ;
        String id= String.valueOf(jsonObject.get("id"));
        List<ArchiveScoreInBookVO> list =archiveScoreBookService.getScoreListInBook(classInfo,id);
        return Result.ok(list);
    }



    @PostMapping ("addTeachNotes")
    @Operation(summary = "添加教学记事")
    public Result<String> addTeachNotes(@RequestBody JSONObject jsonObject){
    String dataForm=String.valueOf(jsonObject.get("dataForm"));
    String bookId= String.valueOf(jsonObject.get("id"));
    archiveScoreBookService.addTeachNotes(dataForm,bookId);
    return Result.ok();
    }



    @PostMapping ("addAnswerNotes")
    @Operation(summary = "添加辅导答疑")
    public Result<String> addAnswerNotes(@RequestBody JSONObject jsonObject){
        String dataForm=String.valueOf(jsonObject.get("dataForm"));
        String bookId= String.valueOf(jsonObject.get("id"));
        archiveScoreBookService.addAnswerNotes(dataForm,bookId);
        return Result.ok();
    }



    @PostMapping("exportScoreBookWord")
    @Operation(summary = "导出记分册")
    public void exportScoreBookWord(@RequestBody JSONObject object, HttpServletResponse response) throws IOException {
        System.out.println(object);
        Long bookId= Long.valueOf(object.get("bookId").toString());
        archiveScoreBookService.createScoreBookWord(bookId,response);
    }

}
