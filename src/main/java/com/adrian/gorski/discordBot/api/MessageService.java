package com.adrian.gorski.discordBot.api;

import com.adrian.gorski.discordBot.config.Bot;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final Bot bot;

    public void sendMessage(String message) {
        bot.getJda().getGuildById("978261544037138533").getTextChannelById("978261544037138536").sendMessage(message).queue();
    }

}
