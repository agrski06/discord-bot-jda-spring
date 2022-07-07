package com.adrian.gorski.discordBot.bot.events;

import com.adrian.gorski.discordBot.bot.command.CommandManager;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

// TODO: add support for editing embed messages with emoji reactions

@RequiredArgsConstructor
public class MessageListener extends ListenerAdapter {

    private final CommandManager manager;

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        manager.handle(event);
    }
}
