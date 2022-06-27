package com.adrian.gorski.discordBot.bot.command.commands;

import com.adrian.gorski.discordBot.bot.command.Command;
import com.adrian.gorski.discordBot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class ShuffleCommand extends Command {

    public ShuffleCommand() {
        aliases = List.of("shuffle", "shu");
    }

    @Override
    public String getName() {
        return "shuffle";
    }

    @Override
    public String getHelp() {
        return "Shuffles current queue";
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        PlayerManager.getInstance().getMusicManager(event.getGuild()).getScheduler().shuffleQueue();
        event.getTextChannel().sendMessage("Queue shuffled!").queue();
    }
}
