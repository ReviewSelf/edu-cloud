package net.edu.module.fallback;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.edu.module.api.EduLessonApi;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class EduLessonApiFallBack implements FallbackFactory<EduLessonApi> {
    @Setter
    private Throwable cause;

    @Override
    public EduLessonApi create(Throwable throwable) {
        log.error("微服务接口调用失败： {}", cause);
        return null;
    }
}