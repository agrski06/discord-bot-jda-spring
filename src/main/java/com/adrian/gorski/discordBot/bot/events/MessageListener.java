package com.adrian.gorski.discordBot.bot.events;

import com.adrian.gorski.discordBot.lavaplayer.GuildMusicManager;
import com.adrian.gorski.discordBot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        if (message.getContentRaw().charAt(0) != '!') return;

        String command;
        String params = "";

        if (message.getContentRaw().contains(" ")) {
            command = message.getContentRaw().substring(1, message.getContentRaw().indexOf(" "));
            params = message.getContentRaw().substring(command.length()+1).strip();
        } else {
            command = message.getContentRaw().substring(1);
        }

        switch (command) {
            case "connect":
                Member author = event.getMember();
                for (VoiceChannel voiceChannel : event.getGuild().getVoiceChannels()) {
                    if (voiceChannel.getMembers().contains(author)) {
                        event.getGuild().getAudioManager().openAudioConnection(voiceChannel);
                        break;
                    }
                }
                break;
            case "disconnect":
                if (event.getGuild().getAudioManager().isConnected()) {
                    event.getGuild().getAudioManager().closeAudioConnection();
                }
                break;
            case "echo":
                event.getChannel().sendMessage(params).queue();
                break;
            case "bruh":
                PlayerManager.getInstance().loadAndPlay(event.getTextChannel(), "https://www.youtube.com/watch?v=dQw4w9WgXcQ");
                break;
            case "stop":
                GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
                musicManager.getScheduler().getPlayer().stopTrack();
                musicManager.getScheduler().getQueue().clear();
                break;
        }

    }
}
