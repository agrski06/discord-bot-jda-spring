package com.adrian.gorski.discordBot.bot.command.commands;

import com.adrian.gorski.discordBot.bot.command.Command;
import com.adrian.gorski.discordBot.bot.command.SimpleCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class ConnectCommand extends SimpleCommand {

    public ConnectCommand() {
        aliases = List.of("connect", "con");
    }

    public void handle(MessageReceivedEvent event) {
        Member author = event.getMember();
        for (VoiceChannel voiceChannel : event.getGuild().getVoiceChannels()) {
            if (voiceChannel.getMembers().contains(author)) {
                event.getGuild().getAudioManager().openAudioConnection(voiceChannel);
                return;
            }
        }
    }

    public String getName() {
        return "connect";
    }

    public String getHelp() {
        return "Connects the Bot to the channel containing the author of the command";
    }

}
