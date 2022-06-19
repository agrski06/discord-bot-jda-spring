package com.adrian.gorski.discordBot.config;

import com.adrian.gorski.discordBot.events.MessageListener;
import com.adrian.gorski.discordBot.events.ReadyListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.login.LoginException;

@Configuration
public class BotConfiguration {

    private final Bot bot;

    @Value("${token}")
    private String token;

    public BotConfiguration(Bot bot) {
        this.bot = bot;
    }

    @Bean
    public void config() throws LoginException {
        JDABuilder builder = JDABuilder.createDefault(token);

        builder.setActivity(Activity.playing("BRUH"));
        builder.addEventListeners(new ReadyListener());
        builder.addEventListeners(new MessageListener());

        bot.setJda(builder.build());
    }
}
