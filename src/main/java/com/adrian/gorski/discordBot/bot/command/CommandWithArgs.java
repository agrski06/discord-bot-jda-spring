package com.adrian.gorski.discordBot.bot.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public abstract class CommandWithArgs extends Command {

    /**
     * Each subclass sets its own aliases
     */
    protected List<String> aliases;

    public abstract void handle(MessageReceivedEvent event, String args);

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }
}
