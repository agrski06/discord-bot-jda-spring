package com.adrian.gorski.discordBot.bot.functionality;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public interface EventlessInterface {

    // info methods
    String getBotImage();
    JDA.Status getStatus();

    // channels
    List<TextChannel> getTextChannels(String serverId);

    // commands
    void sendMessage(String serverId, String message);
    void playSound(String serverId);

}
