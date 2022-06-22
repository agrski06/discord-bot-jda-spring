package com.adrian.gorski.discordBot.api.message;

import com.adrian.gorski.discordBot.bot.config.Bot;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final Bot bot;

    public void sendMessage(String message) {
        Guild guild = bot.getJda().getGuildById("978261544037138533");
        if (guild != null) {
            TextChannel channel = guild.getTextChannelById("978261544037138536");
            if (channel != null) {
                channel.sendMessage(message).queue();
            }
        }
    }

}
