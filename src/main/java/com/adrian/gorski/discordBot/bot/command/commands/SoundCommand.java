package com.adrian.gorski.discordBot.bot.command.commands;

import com.adrian.gorski.discordBot.SoundsMap;
import com.adrian.gorski.discordBot.bot.command.Command;
import com.adrian.gorski.discordBot.bot.functionality.StaticMethods;
import com.adrian.gorski.discordBot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;
import java.util.Map;

public class SoundCommand extends Command {

    private final Map<String, String> sounds;

    public SoundCommand() {
        aliases = List.of("sound", "s");
        doesTakeArgs = true;

        sounds = SoundsMap.getMap();
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        if (!StaticMethods.connectIfDisconnected(event)) return;

        if (!sounds.containsKey(args)) {
            event.getTextChannel().sendMessage("No such sound").queue();
            return;
        }
        PlayerManager.getInstance().loadAndPlay(event.getTextChannel(), sounds.get(args), true);
    }

    @Override
    public String getName() {
        return "sound";
    }

    @Override
    public String getHelp() {
        return "`sound`\n" + "Sounds: " + String.join(", ", sounds.keySet()) + "\nPlays the sound passed as argument";
    }

    public List<String> getSounds() {
        return sounds.keySet().stream().toList();
    }
}
