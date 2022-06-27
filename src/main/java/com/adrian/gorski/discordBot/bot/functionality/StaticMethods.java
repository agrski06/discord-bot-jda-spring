package com.adrian.gorski.discordBot.bot.functionality;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class StaticMethods {

    /**
     * Connects to voice channel if not already connected
     * @param event used for getting context
     * @return true - connected, false - failed to connect
     */
    public static boolean connectIfDisconnected(MessageReceivedEvent event) {
        if (!event.getGuild().getAudioManager().isConnected()) {
            Member author = event.getMember();
            for (VoiceChannel voiceChannel : event.getGuild().getVoiceChannels()) {
                if (voiceChannel.getMembers().contains(author)) {
                    event.getGuild().getAudioManager().openAudioConnection(voiceChannel);
                    return true;
                }
            }
            event.getTextChannel().sendMessage("Failed to connect. (You are probably not connected to the voice channel)").queue();
            return false;
        }
        return true;
    }

}
