package com.itheima.ai.controller;

import com.itheima.ai.config.CommonConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
@RequestMapping( "/ai")
public class ChatController {

    private final ChatClient chatClient;

    @RequestMapping(value = "/chat",produces = "text/html;charset=utf-8")
    public Flux<String> chat(String prompt, String chatId){
        return chatClient.prompt()
                .user(prompt)
                .advisors(a -> a.param(CHAT_ID_KEY, chatId))
                .stream()
                .content();
    }
}
