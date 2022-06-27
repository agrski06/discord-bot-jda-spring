package com.adrian.gorski.discordBot.bot.command.commands;

import com.adrian.gorski.discordBot.bot.command.Command;
import com.adrian.gorski.discordBot.lavaplayer.GuildMusicManager;
import com.adrian.gorski.discordBot.lavaplayer.PlayerManager;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class QueueCommand extends Command {

    public QueueCommand() {
        aliases = List.of("queue");
    }

    @Override
    public String getName() {
        return "queue";
    }

    @Override
    public String getHelp() {
        return "Shows the queued tracks";
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        StringBuilder message = new StringBuilder();
        List<AudioTrack> audioTracks = PlayerManager.getInstance().getMusicManager(event.getGuild()).getScheduler().getQueue().stream().toList();
        int i = 1;
        for (AudioTrack audioTrack : audioTracks) {
            message.append(i++).append(". ")
                    .append("`").append(audioTrack.getInfo().title).append("` by `")
                    .append(audioTrack.getInfo().author).append("`\n");
        }
        if (!message.toString().isEmpty())
            event.getTextChannel().sendMessage(message.toString()).queue();
        else
            event.getTextChannel().sendMessage("Kolejka pusta").queue();
    }
}
