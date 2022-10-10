package net.edu.module.fallback;


import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.edu.module.api.EduTeachApi;
import net.edu.module.api.EduWxApi;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;



@Slf4j
@Component
public class EduWxApiFallBack implements FallbackFactory<EduWxApi> {
    @Setter
    private Throwable cause;

    @Override
    public EduWxApi create(Throwable throwable) {
        log.error("微服务接口调用失败： {}", cause);
        return null;
    }
}
