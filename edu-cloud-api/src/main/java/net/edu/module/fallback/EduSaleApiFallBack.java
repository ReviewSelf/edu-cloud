package net.edu.module.fallback;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.edu.module.api.EduArchiveApi;
import net.edu.module.api.EduSaleApi;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EduSaleApiFallBack implements FallbackFactory<EduSaleApi> {
    @Setter
    private Throwable cause;

    @Override
    public EduSaleApi create(Throwable throwable) {
        log.error("微服务接口调用失败： {}", cause);
        return null;
    }
}
