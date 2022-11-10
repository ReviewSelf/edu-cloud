package net.edu.module.config;

import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.edu.framework.common.utils.UserTokenContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 马佳浩
 * @Date: 2022/11/9 9:49
 * @Version: 1.0
 * @Description:
 */
@Configuration
@ConditionalOnClass(Feign.class)
@AutoConfigureBefore(FeignAutoConfiguration.class)
@Slf4j
public class TokenRequestConfig  {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            if (null != attributes) {
                // 从header获取X-token
                HttpServletRequest request = attributes.getRequest();
                String token = request.getHeader("Authorization");
                if (StringUtils.isBlank(token)) {
                    token = request.getParameter("access_token");
                }
                //将token传递出去
                log.info("Feign Login Request token: {}", token);
                requestTemplate.header("Authorization", token);
            }else {
                String token = UserTokenContext.getToken();
                log.info("Feign no Login token: {}", token);
                requestTemplate.header("Authorization", token);
            }
        };
    }
}
