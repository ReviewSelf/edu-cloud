package net.edu.module.fallback;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.edu.module.api.EduFileApi;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/6 20:21
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Component
public class EduFileApiFallBack implements FallbackFactory<EduFileApi> {
    @Setter
    private Throwable cause;

    @Override
    public EduFileApi create(Throwable throwable) {
        log.error("微服务接口调用失败： {}", cause);
        return null;
    }
}
