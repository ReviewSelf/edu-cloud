package net.edu.module.config;

import net.edu.module.properties.LocalStorageProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

/**
 *
 */
@Configuration
public class LocalResourceConfiguration implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        LocalStorageProperties local = new LocalStorageProperties();
        registry.addResourceHandler("/" + local.getUrl() + "/**")
                .addResourceLocations("file:" + local.getPath() + "/");
    }




}
