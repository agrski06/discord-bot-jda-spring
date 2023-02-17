package com.adrian.gorski.discordBot.bot.command.commands;

import com.adrian.gorski.discordBot.bot.command.Command;
import com.adrian.gorski.discordBot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class ClearCommand extends Command {
    public ClearCommand() {
        aliases = List.of("clear", "cls");
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getHelp() {
        return "Clears the queue";
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        PlayerManager.getInstance().getMusicManager(event.getGuild()).getScheduler().clearQueue();
        event.getChannel().sendMessage("Queue cleared!").queue();
    }
}
