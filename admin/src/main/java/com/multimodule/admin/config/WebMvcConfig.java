package com.multimodule.admin.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig extends WebMvcConfigurationSupport {
    private final GlobIdArgumentResolver globIdArgumentResolver;

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(globIdArgumentResolver);
        super.addArgumentResolvers(argumentResolvers);

    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/docs/**").addResourceLocations("classpath:/static/docs").setCachePeriod(20);
        // vue/static/js/manifest.320b3692f8404593f921.js> 호출 검색 locations
        registry.addResourceHandler("/vue/static/**").addResourceLocations("classpath:/static/vue/static/").setCachePeriod(20);
        super.addResourceHandlers(registry);
    }

}
