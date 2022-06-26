package com.adrian.gorski.discordBot.bot.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public abstract class SimpleCommand extends Command{

    /**
     * Each subclass sets its own aliases
     */
    protected List<String> aliases;

    /**
     * Sets the behaviour of each command that does not get arguments
     * @param event MessageReceivedEvent used for getting context
     */
    public abstract void handle(MessageReceivedEvent event);

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }
}
