package com.adrian.gorski.discordBot.bot.command.commands;

import com.adrian.gorski.discordBot.bot.command.Command;
import com.adrian.gorski.discordBot.lavaplayer.GuildMusicManager;
import com.adrian.gorski.discordBot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.List;

public class DisconnectCommand extends Command {

    public DisconnectCommand() {
        aliases = List.of("disconnect", "dc");
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        AudioManager manager = event.getGuild().getAudioManager();
        if (manager.isConnected()) {
            manager.closeAudioConnection();

            GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
            musicManager.getScheduler().getPlayer().stopTrack();
            musicManager.getScheduler().getQueue().clear();
        }
    }

    @Override
    public String getName() {
        return "disconnect";
    }

    @Override
    public String getHelp() {
        return "Disconnects the bot from the voice channel";
    }

}
