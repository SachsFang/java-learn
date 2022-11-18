package com.fang.springboot.download_template.constant;

import com.fang.springboot.common.util.SpringContextManager;
import com.fang.springboot.download_template.config.TemplateConfig;

/**
 * @author shaobin
 * @date 2022/11/18 11:36
 */
public class TemplateConstant {

    private static TemplateConfig templateConfig = SpringContextManager.getApplicationContext().getBean(TemplateConfig.class);

    public static final String TEMPLATE_PATH = templateConfig.getTemplatePath();

}
