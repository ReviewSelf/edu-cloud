package net.edu.module.api.fallback;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.edu.module.api.EduTeachApi;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/21 10:40
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Component
public class EduTeachApiFallBack implements FallbackFactory<EduTeachApi> {
    @Setter
    private Throwable cause;

    @Override
    public EduTeachApi create(Throwable throwable) {
        log.error("微服务接口调用失败： {}", cause);
        return null;
    }
}
