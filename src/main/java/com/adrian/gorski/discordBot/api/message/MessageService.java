package com.adrian.gorski.discordBot.api.message;

import com.adrian.gorski.discordBot.bot.config.Bot;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final Bot bot;

    public void sendMessage(String serverId, String message, String channelId) {
        Guild guild = bot.getJda().getGuildById(serverId);
        if (guild != null) {
            TextChannel channel = guild.getTextChannelById(channelId);
            if (channel != null) {
                channel.sendMessage(message).queue();
            }
        }
    }

}
