package com.adrian.gorski.discordBot.config;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.JDA;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class Bot {

    private JDA jda;

}
