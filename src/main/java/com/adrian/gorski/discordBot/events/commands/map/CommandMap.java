package com.adrian.gorski.discordBot.events.commands.map;

import com.adrian.gorski.discordBot.events.commands.HelloHandler;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public class CommandMap {
    private static final Map<String, String> commands = new HashMap<>();

    public CommandMap() {
        HelloHandler hello = new HelloHandler();
        commands.put(hello.getCommand(), hello.getResponse());
    }

    public Map<String, String> getCommands() {
        return commands;
    }
}
