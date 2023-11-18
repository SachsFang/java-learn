package com.fang.openai.controller;

import com.fang.openai.api.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shaobin
 * @date 2023/4/24 15:17
 */
@RestController
@RequestMapping(value = "/openai")
public class OpenAiController {

    @Autowired
    private AiService aiService;

    @RequestMapping(value = "chat")
    public String chat() {
        aiService.chat();
        return "ok";
    }
}
