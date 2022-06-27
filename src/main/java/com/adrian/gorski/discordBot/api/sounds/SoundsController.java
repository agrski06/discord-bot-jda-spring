package com.adrian.gorski.discordBot.api.sounds;

import com.adrian.gorski.discordBot.api.error.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
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
        String lang = json.get("lang");
        try {
            if (text.isEmpty()) {
                return new ResponseEntity<>("Provide text!", HttpStatus.BAD_REQUEST);
            }
            service.sendTTS(serverId, text, lang);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException | InterruptedException e) {
            return new ResponseEntity<>(errorService.error(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/get/languages")
    public ResponseEntity<List<String>> getLanguages() {
        return new ResponseEntity<>(service.getLanguages(), HttpStatus.OK);
    }

    @GetMapping("/api/get/sounds")
    public ResponseEntity<List<String>> getSounds() {
        return new ResponseEntity<>(service.getLanguages(), HttpStatus.OK);
    }

}
