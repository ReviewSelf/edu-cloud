package net.edu.module.api.fallback;

import lombok.extern.slf4j.Slf4j;
import net.edu.module.api.CodeProblemSampleApi;
import org.springframework.stereotype.Component;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/4 1:54
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Component
public class CodeProblemSampleFallBack implements CodeProblemSampleApi {

    @Override
    public String helloNacos() {
        return "请求超时了";
    }
}
