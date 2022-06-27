package com.adrian.gorski.discordBot.lavaplayer;

import com.sedmelluq.discord.lavaplayer.filter.equalizer.Equalizer;
import com.sedmelluq.discord.lavaplayer.filter.equalizer.EqualizerFactory;

public class PlayerConfig {

    private final EqualizerFactory equalizer;

    private final float[] BASS_BOOST = {
            0.2f,
            0.15f,
            0.1f,
            0.05f,
            0.0f,
            -0.05f,
            -0.1f,
            -0.1f,
            -0.1f,
            -0.1f,
            -0.1f,
            -0.1f,
            -0.1f,
            -0.1f,
            -0.1f
    };

    public PlayerConfig() {
        this.equalizer = new EqualizerFactory();
    }

    public EqualizerFactory getEqualizer() {
        return equalizer;
    }

    public float[] BASS_BOOST() {
        return BASS_BOOST;
    }
}
