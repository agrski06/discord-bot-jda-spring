package com.adrian.gorski.discordBot.bot.command.commands;

import com.adrian.gorski.discordBot.bot.command.Command;
import com.adrian.gorski.discordBot.bot.functionality.StaticMethods;
import com.adrian.gorski.discordBot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SoundCommand extends Command {

    private final List<String> sounds;

    public SoundCommand() {
        aliases = List.of("sound", "s");
        doesTakeArgs = true;
        sounds = new ArrayList<>();

        sounds.add("bruh");
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        if (!StaticMethods.connectIfDisconnected(event)) return;

        if (!sounds.contains(args)) {
            event.getTextChannel().sendMessage("No such sound").queue();
            return;
        }
        PlayerManager.getInstance().loadAndPlay(event.getTextChannel(), "https://www.youtube.com/watch?v=2ZIpFytCSVc", true);
    }

    @Override
    public String getName() {
        return "sound";
    }

    @Override
    public String getHelp() {
        return "`sound`\n" + "Sounds: " + String.join(", ", sounds) + "\nPlays the sound passed as argument";
    }

}
