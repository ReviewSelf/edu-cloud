package net.edu.module.fallback;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.edu.module.api.EduFileApi;
import net.edu.module.api.EduJudgeApi;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EduJudgeApiFallBack implements FallbackFactory<EduJudgeApi> {
    @Setter
    private Throwable cause;

    @Override
    public EduJudgeApi create(Throwable cause) {
        log.error("微服务接口调用失败： {}", cause);
        return null;
    }
}
