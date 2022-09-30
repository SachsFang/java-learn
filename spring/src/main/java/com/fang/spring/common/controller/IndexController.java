package com.fang.spring.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author shaobin
 * @date 2022/9/29 16:18
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @GetMapping()
    public String toIndex() {
        return "index";
    }
}