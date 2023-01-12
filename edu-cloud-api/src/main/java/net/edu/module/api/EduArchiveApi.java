package net.edu.module.api;

import net.edu.framework.common.utils.Result;
import net.edu.module.fallback.EduArchiveApiFallBack;
import net.edu.module.fallback.EduProblemFallBack;
import net.edu.module.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "edu-cloud-archive", fallbackFactory = EduArchiveApiFallBack.class)
public interface EduArchiveApi {
    /****************************quartz调用******************************************/
    @GetMapping("exam/insertExam")
    Result<Integer> insertExam();
}
