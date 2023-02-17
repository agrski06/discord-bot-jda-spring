package com.adrian.gorski.discordBot.api.sounds;

import com.adrian.gorski.discordBot.bot.command.commands.SoundCommand;
import com.adrian.gorski.discordBot.bot.command.commands.SpeechCommand;
import com.adrian.gorski.discordBot.bot.config.Bot;
import com.adrian.gorski.discordBot.tts.TextToSpeech;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SoundsService {
    private final Bot bot;
    private final SoundRepository soundRepository;

    public void sendTTS(String serverId, String text, String lang, String channelId) throws IOException, InterruptedException {
        Guild guild = bot.getJda().getGuildById(serverId);
        if (guild == null) {
            return;
        }
        TextChannel channel = guild.getTextChannelById(channelId);
        if (channel == null) {
            return;
        }
        TextToSpeech tts = new TextToSpeech();
        tts.speech(guild.getSystemChannel(), text, lang);
    }

    public List<String> getLanguages() {
        return new SpeechCommand().getLangs();
    }

    public List<String> getSounds() {
        return soundRepository.findAll().stream()
                .map(Sound::getName)
                .collect(Collectors.toList());
    }

    public Map<String, String> getSoundsAsMap() {
        return soundRepository.findAll().stream()
                .collect(Collectors.toMap(Sound::getName, Sound::getLink));
    }

    public Sound saveSound(Sound sound) {
        return soundRepository.save(sound);
    }

}
