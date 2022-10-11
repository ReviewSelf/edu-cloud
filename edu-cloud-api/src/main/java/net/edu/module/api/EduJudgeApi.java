package net.edu.module.api;


import net.edu.framework.common.utils.Result;
import net.edu.module.fallback.EduJudgeApiFallBack;
import net.edu.module.vo.LessonJudgeRecordVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "edu-cloud-judge", fallbackFactory = EduJudgeApiFallBack.class)
public interface EduJudgeApi {

    /****************************Lesson调用******************************************/
    @GetMapping("/lesson/problem/record")
    Result<List<LessonJudgeRecordVo>> getLessonProblemRecord(@RequestParam(value = "lessonId") Long lessonId, @RequestParam(value = "type",required = false) Integer type);
}
