package net.edu.module.fallback;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.edu.module.api.EduArchiveApi;
import net.edu.module.api.EduFileApi;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 沈凯文
 * @Date: 2022/11/11 20:21
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Component
public class EduArchiveApiFallBack implements FallbackFactory<EduArchiveApi> {
    @Setter
    private Throwable cause;

    @Override
    public EduArchiveApi create(Throwable throwable) {
        log.error("微服务接口调用失败： {}", cause);
        return null;
    }
}
