package com.adrian.gorski.discordBot.bot.command.commands;

import com.adrian.gorski.discordBot.bot.command.Command;
import com.adrian.gorski.discordBot.lavaplayer.PlayerManager;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class SkipCommand extends Command {
    public SkipCommand() {
        aliases = List.of("skip");
        doesTakeArgs = true;
    }

    @Override
    public String getName() {
        return "skip";
    }

    @Override
    public String getHelp() {
        return "`(sec)`\n"
                + "Skips the track or skips by given amount of seconds";
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        AudioTrack currentTrack = PlayerManager.getInstance().getMusicManager(event.getGuild())
                .getAudioPlayer().getPlayingTrack();

        if (currentTrack != null) {
            if (args.isEmpty() || args.isBlank() || args == null) {
                currentTrack.setPosition(currentTrack.getPosition() + Long.parseLong(args));
            }
            else {
                PlayerManager.getInstance().getMusicManager(event.getGuild()).getScheduler().nextTrack();
            }
        }
    }
}
