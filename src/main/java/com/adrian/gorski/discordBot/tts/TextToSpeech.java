package com.adrian.gorski.discordBot.tts;

import com.adrian.gorski.discordBot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.io.IOException;

public class TextToSpeech {
    public void speech(TextChannel channel, String text, String language) {
        String pythonPath = "C:\\Python310\\python.exe";
        String ttsScriptPath = "C:\\Users\\agrsk\\IdeaProjects\\discordBot\\src\\main\\java\\com\\adrian\\gorski\\discordBot\\tts\\tts.py";

        Process p = null;
        try {
            p = Runtime.getRuntime().exec(pythonPath + " " + ttsScriptPath + " " + text + " " + language);
            p.waitFor();
            PlayerManager.getInstance().loadAndPlay(channel, "speech.mp3", true);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
