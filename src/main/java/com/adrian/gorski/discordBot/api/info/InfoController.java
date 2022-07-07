package com.adrian.gorski.discordBot.api.info;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<Map<String, String>>> getServers() {
        List<Map<String, String>> servers = service.getServers();
        return new ResponseEntity<>(servers, HttpStatus.OK);
    }

    @PostMapping(value = "/api/server-text-channels")
    public ResponseEntity<Map<String, String>> getTextChannels(@RequestBody String guildId) {
        Map<String, String> json = new HashMap<>();

        List<TextChannel> channels = service.getTextChannels(guildId);
        if (channels == null || channels.isEmpty())
            return new ResponseEntity<>(new HashMap<>(), HttpStatus.NO_CONTENT); // OK but no channels found (204)

        for (TextChannel channel : channels) {
            json.put(channel.getId(), channel.getName());
        }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping(value = "/api/server-voice-channels")
    public ResponseEntity<Map<String, String>> getVoiceChannels(@RequestBody String guildId) {
        Map<String, String> json = new HashMap<>();

        List<VoiceChannel> channels = service.getVoiceChannels(guildId);
        if (channels == null || channels.isEmpty())
            return new ResponseEntity<>(new HashMap<>(), HttpStatus.NO_CONTENT); // OK but no channels found (204)

        for (VoiceChannel channel : channels) {
            json.put(channel.getId(), channel.getName());
        }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping(value = "/api/server-voice-channels-connected-number")
    public ResponseEntity<Map<String, String>> getVoiceChannelWithNumberOfUsersConnected(@RequestBody String guildId) {
        Map<String, String> json = new HashMap<>();

        List<VoiceChannel> channels = service.getVoiceChannels(guildId);
        if (channels == null || channels.isEmpty())
            return new ResponseEntity<>(new HashMap<>(), HttpStatus.NO_CONTENT); // OK but no channels found (204)

        for (VoiceChannel channel : channels) {
            json.put(channel.getId(), channel.getName() + " (" + channel.getMembers().size() + ")");
        }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

}
