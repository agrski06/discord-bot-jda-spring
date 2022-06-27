package com.adrian.gorski.discordBot.bot.command.commands;

import com.adrian.gorski.discordBot.bot.command.Command;
import com.adrian.gorski.discordBot.bot.functionality.StaticMethods;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class ConnectCommand extends Command {

    public ConnectCommand() {
        aliases = List.of("connect", "con");
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        StaticMethods.connectIfDisconnected(event);
    }

    @Override
    public String getName() {
        return "connect";
    }

    @Override
    public String getHelp() {
        return "Connects the Bot to the channel containing the author of the command";
    }

}
