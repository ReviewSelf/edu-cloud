package net.edu.module.api;

import io.swagger.v3.oas.annotations.Operation;
import net.edu.framework.common.utils.Result;
import net.edu.module.api.fallback.EduProblemFallBack;
import net.edu.module.api.vo.ProblemPaperItemEntity;
import net.edu.module.api.vo.TeachPlanItemResourceVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "edu-cloud-problem", fallbackFactory = EduProblemFallBack.class)
public interface EduProblemApi {
    @GetMapping("paperItem/{paperId}")
     Result<List<ProblemPaperItemEntity>> getPaperProblem(@PathVariable(value = "paperId") Long paperId);



}
