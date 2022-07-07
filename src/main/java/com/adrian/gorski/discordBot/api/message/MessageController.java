package com.adrian.gorski.discordBot.api.message;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService service;

    @PostMapping("/api/send")
    public void sendMessage(@RequestBody Map<String, String> json) {
        String message = json.get("message");
        String serverId = json.get("serverId");
        String channelId = json.get("channelId");
        service.sendMessage(serverId, message, channelId);
    }
}
