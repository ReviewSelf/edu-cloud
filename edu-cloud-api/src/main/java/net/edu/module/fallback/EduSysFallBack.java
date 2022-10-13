package net.edu.module.fallback;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.edu.module.api.EduProblemApi;
import net.edu.module.api.EduSysApi;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author weng
 * @date 2022/10/13 - 16:28
 **/
@Component
@Slf4j
public class EduSysFallBack implements FallbackFactory<EduSysApi> {
    @Setter
    private Throwable cause;

    @Override
    public EduSysApi create(Throwable throwable) {
        log.error("微服务接口调用失败： {}", cause);
        return null;
    }
}