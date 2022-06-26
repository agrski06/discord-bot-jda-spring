package com.adrian.gorski.discordBot.bot.command.commands;

import com.adrian.gorski.discordBot.bot.command.Command;
import com.adrian.gorski.discordBot.bot.command.CommandWithArgs;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class EchoCommand extends CommandWithArgs {

    public EchoCommand() {
        aliases = List.of("echo");
    }

    @Override
    public void handle(MessageReceivedEvent event, String args) {
        event.getChannel().sendMessage(args).queue();
    }

    @Override
    public String getName() {
        return "echo";
    }

    @Override
    public String getHelp() {
        return "Types whatever you type";
    }
}
