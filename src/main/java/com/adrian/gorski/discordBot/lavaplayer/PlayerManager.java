package com.adrian.gorski.discordBot.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioConfiguration;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.HashMap;
import java.util.Map;

public class PlayerManager {
    private static PlayerManager INSTANCE;

    private final PlayerConfig playerConfig;

    private final Map<Long, GuildMusicManager> musicManagers;
    private final AudioPlayerManager audioPlayerManager;

    public PlayerManager() {
        this.playerConfig = new PlayerConfig();
        this.musicManagers = new HashMap<>();
        this.audioPlayerManager = new DefaultAudioPlayerManager();

        AudioSourceManagers.registerRemoteSources(this.audioPlayerManager);
        AudioSourceManagers.registerLocalSource(this.audioPlayerManager);
    }

    public GuildMusicManager getMusicManager(Guild guild) {
        return this.musicManagers.computeIfAbsent(guild.getIdLong(), (guildId) -> {
            final GuildMusicManager guildMusicManager = new GuildMusicManager(this.audioPlayerManager);

            guild.getAudioManager().setSendingHandler(guildMusicManager.getSendHandler());

            return guildMusicManager;
        });
    }

    public void setBassBoost(Guild guild, float percentage) {
        this.getMusicManager(guild).getAudioPlayer().setFilterFactory(this.playerConfig.getEqualizer());
        this.getMusicManager(guild).getAudioPlayer().setFrameBufferDuration(250);

        this.getAudioPlayerManager().getConfiguration().setFilterHotSwapEnabled(true);

        final float multiplier = percentage / 100.00f;
        for (int i = 0; i < playerConfig.BASS_BOOST().length; i++) {
            this.playerConfig.getEqualizer().setGain(i, this.playerConfig.BASS_BOOST()[i] * multiplier);
        }
    }

    public void loadAndPlay(TextChannel channel, String trackUrl, boolean silent) {
        final GuildMusicManager musicManager = this.getMusicManager(channel.getGuild());

        this.audioPlayerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {

                musicManager.getScheduler().queue(track);

                if (!silent)
                    channel.sendMessage("Adding to queue: `")
                            .append(track.getInfo().title)
                            .append("` by `")
                            .append(track.getInfo().author)
                            .append('`')
                            .queue();
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                // Queues only the first search result
                if (!playlist.isSearchResult())
                    for (AudioTrack track : playlist.getTracks())
                        musicManager.getScheduler().queue(track);
                else
                    musicManager.getScheduler().queue(playlist.getTracks().get(0));

                if (!silent)
                    channel.sendMessage("Added to queue: `")
                            .append(playlist.getName())
                            .append("`")
                            .queue();
            }

            @Override
            public void noMatches() {
                channel.sendMessage("No matches for given link").queue();
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                //
            }
        });
    }

    public static PlayerManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerManager();
        }

        return INSTANCE;
    }

    public AudioPlayerManager getAudioPlayerManager() {
        return audioPlayerManager;
    }
}