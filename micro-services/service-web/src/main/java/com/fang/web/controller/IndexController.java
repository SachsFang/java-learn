package com.fang.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shaobin
 * @date 2022/12/30 17:11
 */
@RestController
@RequestMapping("/index")
@RefreshScope //Nocos config 的注解，配置可在项目启动时刷新配置文件
public class IndexController {

    @Value("${author.name}")
    private String authorName;

    /**
     * 通过nacos配置中心获取配置文件信息
     * @return
     */
    @RequestMapping("/getAuthorNameByNacosConfig")
    public String getAuthorNameByNacosConfig() {
        return authorName;
    }
}
