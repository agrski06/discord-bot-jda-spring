package com.adrian.gorski.discordBot.events.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Command {
    protected String command;
    protected String response;

    public Command(String command) {
        this.command = command;
        this.response = generateResponse();
    }

    public abstract String generateResponse();

}
