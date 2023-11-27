package com.fang.springboot.common.config;

import com.fang.springboot.common.format.datetime.SmartDateConverter;
import com.fang.springboot.common.format.datetime.SmartDateFormatter;
import com.fang.springboot.common.reslover.DateHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 注入参数解析器
 *
 * @author shaobin
 * @date 2023/11/27 15:47
 */
@Configuration
public class WebMvcParamConfig implements WebMvcConfigurer {

    private SmartDateFormatter smartDateFormatter;

    public WebMvcParamConfig(SmartDateFormatter smartDateFormatter) {
        this.smartDateFormatter = smartDateFormatter;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new DateHandlerMethodArgumentResolver(smartDateFormatter));
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new SmartDateConverter());
    }

}
