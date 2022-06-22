package com.adrian.gorski.discordBot.api.info;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InfoController {

    private final InfoService service;
    private static final Gson gson = new Gson();

    @GetMapping("/api/bot-avatar")
    public ResponseEntity<String> getAvatar() {
        return new ResponseEntity<>(service.getAvatar(), HttpStatus.OK);
    }

    @GetMapping("/api/bot-name")
    public ResponseEntity<String> getName() {
        return new ResponseEntity<>(service.getName(), HttpStatus.OK);
    }

    @GetMapping("/api/bot-status")
    public ResponseEntity<String> getStatus() {
        return new ResponseEntity<>(service.getStatus().toString(), HttpStatus.OK);
    }

    @GetMapping(value = "/api/bot-servers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getServers() {
        List<String> servers = service.getServers();
        return new ResponseEntity<>(gson.toJson(servers), HttpStatus.OK);
    }
}
