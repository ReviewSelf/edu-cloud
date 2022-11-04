package net.edu.module.interceptor;

import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.edu.framework.common.config.UserTokenContext;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 马佳浩
 * @Date: 2022/11/4 12:16
 * @Version: 1.0
 * @Description:
 */
@Component
@Slf4j
public class FeignRequestInterceptor  implements RequestInterceptor{

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null != attributes) {
            HttpServletRequest request = attributes.getRequest();
            log.debug("Feign request: {}", request.getRequestURI());
            // 将token信息放入header中
            String token = request.getHeader("Authorization");
            if(token==null || "".equals(token)){
                token = request.getParameter("access_token");
            }
            log.info("Feign Login Request token: {}", token);
            requestTemplate.header("Authorization", token);
        }else{
            String token = UserTokenContext.getToken();

            log.info("Feign no Login token: {}", token);
            requestTemplate.header("Authorization", token);
        }

    }
}
