package com.adrian.gorski.discordBot.bot.command.commands;

import com.adrian.gorski.discordBot.bot.command.Command;
import com.adrian.gorski.discordBot.lavaplayer.PlayerManager;
import com.sedmelluq.discord.lavaplayer.filter.equalizer.EqualizerFactory;
import com.sedmelluq.discord.lavaplayer.player.AudioConfiguration;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class BassCommand extends Command {

    public BassCommand() {
        aliases = List.of("bass");
        doesTakeArgs = true;
    }

    @Override
    public String getName() {
        return "bass";
    }

    @Override
    public String getHelp() {
        return "`percentage`\n"
                + "Bass boost";
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        PlayerManager.getInstance().setBassBoost(event.getGuild(), Float.parseFloat(args));
    }
}
