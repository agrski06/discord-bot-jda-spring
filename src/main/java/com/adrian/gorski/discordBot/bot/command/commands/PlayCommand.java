package com.adrian.gorski.discordBot.bot.command.commands;

import com.adrian.gorski.discordBot.bot.command.Command;
import com.adrian.gorski.discordBot.bot.functionality.StaticMethods;
import com.adrian.gorski.discordBot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class PlayCommand extends Command {

    public PlayCommand() {
        aliases = List.of("play", "p");
        doesTakeArgs = true;
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        StaticMethods.connectIfDisconnected(event);

        args = args.strip();
        PlayerManager.getInstance().loadAndPlay(event.getTextChannel(), args, false);
    }

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getHelp() {
        return "!play <link> - Plays music!";
    }
}
