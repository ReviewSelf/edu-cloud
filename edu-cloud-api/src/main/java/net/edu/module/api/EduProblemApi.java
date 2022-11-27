package net.edu.module.api;


import io.swagger.v3.oas.annotations.Operation;
import net.edu.framework.common.utils.Result;

import net.edu.module.fallback.EduProblemFallBack;
import net.edu.module.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient(value = "edu-cloud-problem", fallbackFactory = EduProblemFallBack.class)
public interface EduProblemApi {

    /****************************Judge调用******************************************/
    //获取测试样例
    @GetMapping("sample/list/{problemId}")
    Result<List<CodeSampleVO>> getSampleList(@PathVariable(value = "problemId") Long  problemId);

    @GetMapping("choice/options/{problemId}")
    Result<List<String>> getChoiceOptions(@PathVariable(value = "problemId") Long problemId,@RequestParam(value = "flag",required = false)int flag);

    @GetMapping("choice/submitTimes")
    Result<String> updateChoiceSubmitTimes(@RequestParam(value = "id") Long id , @RequestParam(value = "isTrue") Boolean isTrue );

    @GetMapping("code/submitTimes")
    Result<String> updateCodeSubmitTimes(@RequestParam(value = "id") Long id , @RequestParam(value = "isTrue") Boolean isTrue );

    @GetMapping("fill/submitTimes")
    Result<String> updateFillSubmitTimes(@RequestParam(value = "id") Long id , @RequestParam(value = "isTrue") Boolean isTrue );


    /****************************lessons调用******************************************/
    @GetMapping("paperItem/{paperId}")
    Result<List<ProblemPaperItemEntity>> getPaperProblem(@PathVariable(value = "paperId") Long paperId);

    @GetMapping("choice/problemInfo/{problemId}")
    Result<ChoiceProblemVO> getChoiceProblemInfo(@PathVariable("problemId") Long problemId);

    @GetMapping("code/problemInfo/{problemId}")
    Result<CodeProblemVO> getCodeProblemInfo(@PathVariable("problemId") Long problemId);

    @GetMapping("fill/problemInfo/{problemId}")
    Result<FillProblemVO> getFillProblemInfo(@PathVariable("problemId")  Long problemId);

    @GetMapping("ability/getUserAbility")
    Result<AbilityUserVo> getUserAbility(@RequestParam("userId") Long userId);

    @GetMapping("ability/list")
    Result<List<AbilityVO>> getAbilityList();
}
