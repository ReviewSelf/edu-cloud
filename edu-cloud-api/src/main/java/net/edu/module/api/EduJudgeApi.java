package net.edu.module.api;


import io.swagger.v3.oas.annotations.Operation;
import net.edu.framework.common.utils.Result;
import net.edu.module.fallback.EduJudgeApiFallBack;
import net.edu.module.vo.LessonJudgeRecordVo;
import net.edu.module.vo.LessonProblemRankVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "edu-cloud-judge", fallbackFactory = EduJudgeApiFallBack.class)
public interface EduJudgeApi {

    /****************************Lesson调用******************************************/
    @GetMapping("/lesson/record/getLessonProblemRank")
    Result<List<LessonProblemRankVO>> getLessonProblemRank(@RequestParam( value ="lessonId") Long lessonId,
                                                                  @RequestParam(value = "type")Integer type);

}
