package com.adrian.gorski.discordBot.api.info;

import com.adrian.gorski.discordBot.bot.config.Bot;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<String> getServers() {
        List<Guild> guilds = bot.getJda().getGuilds();
        List<String> result = guilds.stream().map(item -> {
            String iconUrl = item.getIconUrl();

            return "{\"icon\": \"" + (iconUrl == null ? "https://cdn.discordapp.com/embed/avatars/0.png" : iconUrl) + "\", "
                    + "\"name\": \"" + item.getName() + "\", "
                    + "\"id\": \"" + item.getId() + "\""
                    + "}";
        }).toList();

        return result;
    }

}
