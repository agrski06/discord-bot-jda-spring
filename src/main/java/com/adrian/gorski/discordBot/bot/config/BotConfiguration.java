package com.adrian.gorski.discordBot.bot.config;

import com.adrian.gorski.discordBot.bot.command.CommandManager;
import com.adrian.gorski.discordBot.bot.events.MessageListener;
import com.adrian.gorski.discordBot.bot.events.ReadyListener;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BotConfiguration {

    private final Bot bot;
    private final CommandManager commandManager;

    @Value("${token}")
    private String token;

    @Bean
    public void config() {
        JDABuilder builder = JDABuilder.createDefault(token);

        builder.setActivity(Activity.playing("BRUH"));
        builder.addEventListeners(new ReadyListener());
        builder.addEventListeners(new MessageListener(commandManager));
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);

        bot.setJda(builder.build());
    }
}
