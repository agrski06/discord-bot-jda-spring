package com.adrian.gorski.discordBot.bot.functionality;

import java.util.Collection;

public interface EventlessInterface {
    // info methods
    void getBotImage();
    void getStatus();

    // channels
    void getChannels();

    // commands
    void sendMessage(String message);
    void playSound();

}
