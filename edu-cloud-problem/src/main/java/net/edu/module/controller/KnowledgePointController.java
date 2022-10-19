package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.constant.Constant;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.KnowledgePointConvert;
import net.edu.module.entity.KnowledgePointEntity;
import net.edu.module.service.KnowledgePointService;
import net.edu.module.vo.KnowledgePointVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 选择题库表
 *
 * @author 马佳浩
 * @since 1.0.0 2022-09-05
 */
@RestController
@RequestMapping("kp")
@Tag(name="知识点表")
@AllArgsConstructor
public class KnowledgePointController {

    private final KnowledgePointService knowledgePointService;

    @GetMapping("list")
    @Operation(summary = "知识点列表")
    public Result<List<KnowledgePointVO>> list(){

        List<KnowledgePointVO> list = knowledgePointService.getKpList();
        return Result.ok(list);
    }


    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<KnowledgePointVO> get(@PathVariable("id") Long id){
        KnowledgePointEntity entity = knowledgePointService.getById(id);
        KnowledgePointVO vo = KnowledgePointConvert.INSTANCE.convert(entity);
        // 获取上级菜单名称
//        if(!Constant.ROOT.equals(entity.getPid())){
//            KnowledgePointEntity parentEntity = knowledgePointService.getById(entity.getPid());
//            vo.setParentName(parentEntity.getName());
//        }
        return Result.ok(vo);
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody @Valid KnowledgePointVO vo){
        knowledgePointService.save(vo);
        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid KnowledgePointVO vo){
        knowledgePointService.update(vo);

        return Result.ok();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "删除")
    public Result<String> delete(@PathVariable("id") Long id){
        // 判断是否有子知识点
        Long count = knowledgePointService.getSubKpCount(id);
        if(count > 0){
            return Result.error("请先删除子知识点");
        }
        knowledgePointService.delete(id);
        return Result.ok();
    }

    @GetMapping("/name/{id}")
    @Operation(summary = "获取知识点名称")
    public Result<String> getName(@PathVariable("id") Long id){
        KnowledgePointEntity entity = knowledgePointService.getById(id);
        return Result.ok(entity.getName());
    }
}
