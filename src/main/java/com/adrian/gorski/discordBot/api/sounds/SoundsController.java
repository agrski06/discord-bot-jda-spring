package com.adrian.gorski.discordBot.api.sounds;

import com.adrian.gorski.discordBot.api.error.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SoundsController {

    private final SoundsService service;
    private final ErrorService errorService;

    @PostMapping("/api/tts")
    public ResponseEntity<String> sendMessage(@RequestBody Map<String, String> json) {
        String text = json.get("text");
        String serverId = json.get("serverId");
        try {
            if (text.isEmpty()) {
                return new ResponseEntity<>("Provide text!", HttpStatus.BAD_REQUEST);
            }
            service.sendTTS(serverId, text);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException | InterruptedException e) {
            return new ResponseEntity<>(errorService.error(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
