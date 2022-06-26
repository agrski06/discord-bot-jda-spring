package com.adrian.gorski.discordBot.bot.command.commands;

import com.adrian.gorski.discordBot.bot.command.Command;
import com.adrian.gorski.discordBot.bot.functionality.StaticMethods;
import com.adrian.gorski.discordBot.tts.TextToSpeech;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class SpeechCommand extends Command {

    public SpeechCommand() {
        aliases = List.of("speech", "sp");
        doesTakeArgs = true;
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        StaticMethods.connectIfDisconnected(event);

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
