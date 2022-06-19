package com.adrian.gorski.discordBot.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService service;

    @PostMapping("/api/send")
    public void sendMessage(@RequestBody String message) {
        service.sendMessage(message);
    }
}
