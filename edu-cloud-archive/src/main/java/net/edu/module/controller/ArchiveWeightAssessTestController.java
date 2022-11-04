package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.service.ArchiveWeightAssessTestService;
import net.edu.module.vo.ArchiveWeightAssessTestVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author weng
 * @date 2022/11/3 - 11:44
 **/
@RestController
@RequestMapping("weightTest")
@Tag(name="评测点权重")
@AllArgsConstructor
public class ArchiveWeightAssessTestController {

    private final ArchiveWeightAssessTestService archiveWeightAssessTestService;

    @PostMapping("test")
    @Operation(summary = "插入评测点权重")
    public Result<Integer> insertTestWeight(@RequestBody List<ArchiveWeightAssessTestVO> vos){
        return Result.ok(archiveWeightAssessTestService.insertTestWeight(vos));
    }
}