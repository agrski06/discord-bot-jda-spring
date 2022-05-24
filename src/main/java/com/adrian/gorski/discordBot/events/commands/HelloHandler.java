package com.adrian.gorski.discordBot.events.commands;

public class HelloHandler extends Command {

    public HelloHandler() {
        super("hello");
    }

    @Override
    public String generateResponse() {
        return "Hello!";
    }
}
