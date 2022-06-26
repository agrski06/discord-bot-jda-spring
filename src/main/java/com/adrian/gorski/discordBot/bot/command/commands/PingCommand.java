package com.adrian.gorski.discordBot.bot.command.commands;

import com.adrian.gorski.discordBot.bot.command.Command;
import com.adrian.gorski.discordBot.bot.command.SimpleCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class PingCommand extends SimpleCommand {

    public PingCommand() {
        aliases = List.of("ping");
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        event.getJDA().getRestPing().queue(
                ping -> event.getTextChannel().sendMessage("Rest ping: " + ping).queue()
        );
    }

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public String getHelp() {
        return "Returns Rest ping";
    }

}
