package com.adrian.gorski.discordBot.api.info;

import com.adrian.gorski.discordBot.bot.config.Bot;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InfoService {

    private final Bot bot;
    private static final Gson gson = new Gson();

    public String getAvatar() {
        return bot.getJda().getSelfUser().getEffectiveAvatarUrl();
    }

    public String getName() {
        return bot.getJda().getSelfUser().getName();
    }

    public JDA.Status getStatus() {
        return bot.getJda().getStatus();
    }

    public List<Map<String, String>> getServers() {
        List<Guild> guilds = bot.getJda().getGuilds();
        List<Map<String, String>> result = guilds.stream().map(item -> {
            String iconUrl = item.getIconUrl();

            Map<String, String> json = new HashMap<>();
            json.put("icon", (iconUrl == null ? "https://cdn.discordapp.com/embed/avatars/0.png" : iconUrl));
            json.put("name", item.getName());
            json.put("id", item.getId());

            return json;
        }).toList();

        return result;
    }

    @Nullable
    public List<TextChannel> getTextChannels(String guildId) {
        Guild guild = bot.getJda().getGuildById(guildId);
        if (guild != null)
            return guild.getTextChannels();
        return null;
    }

    @Nullable
    public List<VoiceChannel> getVoiceChannels(String guildId) {
        Guild guild = bot.getJda().getGuildById(guildId);
        if (guild != null)
            return guild.getVoiceChannels();
        return null;
    }

}
