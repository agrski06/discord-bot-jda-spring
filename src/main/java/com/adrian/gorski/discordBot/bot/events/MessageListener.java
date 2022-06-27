package com.adrian.gorski.discordBot.bot.events;

import com.adrian.gorski.discordBot.bot.command.CommandManager;
import com.adrian.gorski.discordBot.lavaplayer.GuildMusicManager;
import com.adrian.gorski.discordBot.lavaplayer.PlayerManager;
import com.adrian.gorski.discordBot.tts.TextToSpeech;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.script.ScriptException;
import java.util.Arrays;

@RequiredArgsConstructor
public class MessageListener extends ListenerAdapter {

    private final CommandManager manager;

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        manager.handle(event);
    }
}
