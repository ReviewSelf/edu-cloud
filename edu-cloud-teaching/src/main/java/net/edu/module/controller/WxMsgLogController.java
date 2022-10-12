package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.WxMsgLogConvert;
import net.edu.module.entity.WxMsgLogEntity;
import net.edu.module.service.WxMsgLogService;
import net.edu.module.query.WxMsgLogQuery;
import net.edu.module.vo.WxMsgLogVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 消息推送
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-11
*/
@RestController
@RequestMapping("wxmsglog")
@Tag(name="消息推送")
@AllArgsConstructor
public class WxMsgLogController {
    private final WxMsgLogService wxMsgLogService;

    @GetMapping("page")
    @Operation(summary = "分页")

    public Result<PageResult<WxMsgLogEntity>> page(@Valid WxMsgLogQuery query){

        PageResult<WxMsgLogEntity> page = wxMsgLogService.page(query);
        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<WxMsgLogEntity> get(@PathVariable("id") Long id){
        return Result.ok(wxMsgLogService.selectById(id));
    }


    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        wxMsgLogService.delete(idList);
        return Result.ok();
    }
}