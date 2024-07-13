package com.fang.springboot.common.functions_module.download_template.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author shaobin
 * @date 2022/11/18 11:32
 */
@Component
@ConfigurationProperties("learn.template")
@Data
public class TemplateConfig {

    private String templatePath;
}
