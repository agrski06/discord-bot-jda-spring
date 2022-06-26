package com.adrian.gorski.discordBot.bot.functionality;

import com.adrian.gorski.discordBot.bot.config.Bot;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class EventlessCommands implements EventlessInterface {

    private final Bot bot;

    @Override
    public String getBotImage() {
        return bot.getJda().getSelfUser().getEffectiveAvatarUrl();
    }

    @Override
    public JDA.Status getStatus() {
        return bot.getJda().getStatus();
    }

    @Override
    public List<TextChannel> getTextChannels(String serverId) {
        return Objects.requireNonNull(bot.getJda().getGuildById(serverId)).getTextChannels();
    }

    @Override
    public void sendMessage(String serverId, String message) {
        Guild guild = bot.getJda().getGuildById(serverId);
        if (guild != null) {
            TextChannel channel = guild.getTextChannels().get(0);
            if (channel != null) {
                channel.sendMessage(message).queue();
            }
        }
    }

    @Override
    public void playSound(String serverId) {
        //
    }
}
