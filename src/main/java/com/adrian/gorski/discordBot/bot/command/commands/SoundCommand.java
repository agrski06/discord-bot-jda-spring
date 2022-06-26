package com.adrian.gorski.discordBot.bot.command.commands;

import com.adrian.gorski.discordBot.bot.command.CommandWithArgs;
import com.adrian.gorski.discordBot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class SoundCommand extends CommandWithArgs {

    private final List<String> sounds;

    public SoundCommand() {
        aliases = List.of("sound", "s");
        sounds = new ArrayList<>();

        sounds.add("bruh");
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
        return "Plays the sound passed as argument";
    }

}
