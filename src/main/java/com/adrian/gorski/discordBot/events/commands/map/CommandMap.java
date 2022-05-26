package com.adrian.gorski.discordBot.events.commands.map;

import com.adrian.gorski.discordBot.events.commands.Command;
import com.adrian.gorski.discordBot.events.commands.EchoHandler;
import com.adrian.gorski.discordBot.events.commands.HelloHandler;

import java.util.HashMap;

public class CommandMap extends HashMap<String, String> {

    public CommandMap() {
        super();
        HelloHandler hello = new HelloHandler();
        EchoHandler echo = new EchoHandler();
        this.put(hello.getCommand(), hello.getResponse());
        this.put(echo.getCommand(), echo.getResponse());
    }

}
