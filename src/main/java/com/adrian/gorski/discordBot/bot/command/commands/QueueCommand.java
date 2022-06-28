package com.adrian.gorski.discordBot.bot.command.commands;

import com.adrian.gorski.discordBot.bot.command.Command;
import com.adrian.gorski.discordBot.lavaplayer.PlayerManager;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
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
        List<AudioTrack> audioTracks = PlayerManager.getInstance().getMusicManager(event.getGuild())
                .getScheduler().getQueue().stream().toList();

        AudioTrack currentTrack = PlayerManager.getInstance().getMusicManager(event.getGuild())
                .getAudioPlayer().getPlayingTrack();

        if (PlayerManager.getInstance().getMusicManager(event.getGuild()).getScheduler().getQueue().isEmpty()
                && currentTrack == null) {
            event.getTextChannel().sendMessage("Queue empty").queue();
            return;
        }

        EmbedBuilder eb = new EmbedBuilder()
                .setColor(new Color(2553434))
                .setAuthor("Queue:", null, null)
                .addField("Playing:", "[" + currentTrack.getInfo().title + "](" + currentTrack.getInfo().uri + ")", false);

        String url = currentTrack.getInfo().uri;
        if (isUrl(url)) {
            eb.setThumbnail("https://img.youtube.com/vi/"
                    + url.substring(url.indexOf("v=")+2)
                    + "/0.jpg");
        }

        // If queue is empty then to not show the rest of embed
        if (PlayerManager.getInstance().getMusicManager(event.getGuild()).getScheduler().getQueue().isEmpty()) {
            event.getTextChannel().sendMessageEmbeds(eb.build()).queue();
            return;
        }

        int i = 1;
        for (AudioTrack audioTrack : audioTracks) {
            if (isUrl(audioTrack.getInfo().uri))
                eb.addField((i++) + ". " + audioTrack.getInfo().title, audioTrack.getInfo().author, false);
            else
                eb.addField((i++) + ". " + "Text to speech", " ", false);
        }

        event.getTextChannel().sendMessageEmbeds(eb.build()).queue();

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
