package com.fang.openai.service;

import com.fang.openai.api.AiService;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author shaobin
 * @date 2023/4/18 17:31
 */
@Service
public class AiServiceImpl implements AiService {

    @Value("${openai.key}")
    private String key;

    @Override
    public void chat() {
        OpenAiService openAiService = new OpenAiService(key);
//        ChatCompletionRequest request = new ChatCompletionRequest();
//        openAiService.createChatCompletion()


    }
}
