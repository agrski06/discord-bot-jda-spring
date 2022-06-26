package com.adrian.gorski.discordBot.bot.command.commands;

import com.adrian.gorski.discordBot.bot.command.CommandWithArgs;
import com.adrian.gorski.discordBot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class PlayCommand extends CommandWithArgs {

    public PlayCommand() {
        aliases = List.of("play", "p");
    }

    @Override
    public void handle(MessageReceivedEvent event, String args) {
        if (!event.getGuild().getAudioManager().isConnected()) {
            Member author = event.getMember();
            for (VoiceChannel voiceChannel : event.getGuild().getVoiceChannels()) {
                if (voiceChannel.getMembers().contains(author)) {
                    event.getGuild().getAudioManager().openAudioConnection(voiceChannel);
                    break;
                }
            }
        }
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
