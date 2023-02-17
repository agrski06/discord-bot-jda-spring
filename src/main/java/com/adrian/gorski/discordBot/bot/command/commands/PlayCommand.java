package com.adrian.gorski.discordBot.bot.command.commands;

import com.adrian.gorski.discordBot.bot.command.Command;
import com.adrian.gorski.discordBot.bot.functionality.StaticMethods;
import com.adrian.gorski.discordBot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class PlayCommand extends Command {

    public PlayCommand() {
        aliases = List.of("play", "p");
        doesTakeArgs = true;
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        if (!StaticMethods.connectIfDisconnected(event)) return;

        args = args.strip();
        if (isUrl(args)) {
            PlayerManager.getInstance().loadAndPlay(event.getChannel().asTextChannel(), args, false);
        } else {
            PlayerManager.getInstance().loadAndPlay(event.getChannel().asTextChannel(), "ytsearch:" + args, false);
        }
    }

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getHelp() {
        return "`link` or `search phrase` \nPlays music!";
    }

    private boolean isUrl(String text) {
        try {
            new URL(text);
        } catch (MalformedURLException e) {
            return false;
        }
        return true;
    }

}
