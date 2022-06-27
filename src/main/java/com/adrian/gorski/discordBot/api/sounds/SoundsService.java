package com.adrian.gorski.discordBot.api.sounds;

import com.adrian.gorski.discordBot.bot.command.commands.SoundCommand;
import com.adrian.gorski.discordBot.bot.command.commands.SpeechCommand;
import com.adrian.gorski.discordBot.bot.config.Bot;
import com.adrian.gorski.discordBot.tts.TextToSpeech;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SoundsService {
    private final Bot bot;

    public void sendTTS(String serverId, String text, String lang) throws IOException, InterruptedException {
        Guild guild = bot.getJda().getGuildById(serverId);
        if (guild != null) {
            TextChannel channel = guild.getTextChannels().get(0);
            if (channel != null) {
                TextToSpeech tts = new TextToSpeech();
                tts.speech(guild.getSystemChannel(), text, lang);
            }
        }
    }

    public List<String> getLanguages() {
        return new SpeechCommand().getLangs();
    }

    public List<String> getSounds() {
        return new SoundCommand().getSounds();
    }

}
