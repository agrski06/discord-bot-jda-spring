package com.adrian.gorski.discordBot.bot.command.commands;

import com.adrian.gorski.discordBot.bot.command.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class EchoCommand extends Command {

    public EchoCommand() {
        aliases = List.of("echo");
        doesTakeArgs = true;
    }

    @Override
    public void handle(MessageReceivedEvent event) {
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
