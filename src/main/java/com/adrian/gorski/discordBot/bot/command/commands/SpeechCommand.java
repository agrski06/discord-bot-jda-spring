package com.adrian.gorski.discordBot.bot.command.commands;

import com.adrian.gorski.discordBot.bot.command.CommandWithArgs;
import com.adrian.gorski.discordBot.tts.TextToSpeech;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class SpeechCommand extends CommandWithArgs {

    public SpeechCommand() {
        aliases = List.of("speech", "sp");
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
        TextToSpeech tts = new TextToSpeech();
        try {
            StringBuilder text = new StringBuilder();
            String[] paramsWords = args.split(" ");
            for (String paramsWord : paramsWords) {
                text.append(paramsWord);
            }
            tts.speech(event.getTextChannel(), text.toString(), "pl");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getName() {
        return "speech";
    }

    @Override
    public String getHelp() {
        return "Speaks whatever you type";
    }

}
