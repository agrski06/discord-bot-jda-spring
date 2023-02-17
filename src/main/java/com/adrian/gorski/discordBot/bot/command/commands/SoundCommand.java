package com.adrian.gorski.discordBot.bot.command.commands;

import com.adrian.gorski.discordBot.api.sounds.SoundsService;
import com.adrian.gorski.discordBot.bot.command.Command;
import com.adrian.gorski.discordBot.bot.functionality.StaticMethods;
import com.adrian.gorski.discordBot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SoundCommand extends Command {

    /**
     * V: name
     * K: URL
     */
    private final Map<String, String> sounds;

    public SoundCommand(SoundsService soundsService) {
        aliases = List.of("sound", "s");
        doesTakeArgs = true;

        sounds = soundsService.getSoundsAsMap();
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        if (!StaticMethods.connectIfDisconnected(event)) return;

        if (!sounds.containsKey(args)) {
            event.getChannel().sendMessage("No such sound").queue();
            return;
        }
        PlayerManager.getInstance().loadAndPlay(event.getChannel().asTextChannel(), sounds.get(args), true);
    }

    @Override
    public String getName() {
        return "sound";
    }

    @Override
    public String getHelp() {
        return "`sound`\n" + "Sounds: " + String.join(", ", sounds.keySet()) + "\nPlays the sound passed as argument";
    }

}
