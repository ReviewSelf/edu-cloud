package net.edu.module.api;

import net.edu.framework.common.utils.Result;
import net.edu.module.fallback.EduProblemFallBack;
import net.edu.module.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "edu-cloud-wechat", fallbackFactory = EduProblemFallBack.class)
public interface EduWxApi {
    /****************************quartz调用******************************************/
    @GetMapping("wx/getAccessToken")
    void getAccessToken();

    @RequestMapping(value = "wx/send", method = RequestMethod.POST)
    void sendTemplateMessage();

    /****************************微信推送调用******************************************/

    @PostMapping("classOpen")
    Result<String> insertClassOpenTemplate(@RequestBody List<WxClassOpenVO> vo);

    @PostMapping("workPublish")
    Result<String> insertWorkPublishTemplate(@RequestBody List<WxWorkPublishVO> vo);

    @PostMapping("lessonOpen")
    Result<String> insertLessonOpenTemplate(@RequestBody List<WxLessonOpenVO> vo);

    @PostMapping("signSuccess")
    Result<String> insertSignSuccessTemplate(@RequestBody List<WxSignSuccessVO> vo);

    @PostMapping("workDeadline")
    Result<String> insertWorkDeadlineTemplate(@RequestBody List<WxWorkDeadlineVO> vo);

    @PostMapping("lessonEvaluation")
    Result<String> insertLessonEvaluationTemplate(@RequestBody List<WxLessonEvaluationVO> vo);
}
