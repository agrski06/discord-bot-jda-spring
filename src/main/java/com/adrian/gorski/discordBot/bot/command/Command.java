package com.adrian.gorski.discordBot.bot.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Command {

    /**
     * Holds list of aliases by which the command can be called.
     * Each command sets its own aliases.
     */
    protected List<String> aliases;

    protected boolean doesTakeArgs = false;
    protected String args;

    protected char parameterPrefix = '-';

    /**
     * @return name of the command
     */
    public abstract String getName();

    /**
     * @return help in String format
     */
    public abstract String getHelp();

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

    public boolean doesTakeArgs() {
        return doesTakeArgs;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    /**
     * Returns arguments from given text as String, assuming that it is passed from message.getContentRaw()
     * @param rawText text from message.getContentRaw()
     * @return arguments of a command
     */
    public String extractArgsAsString(String rawText) {
        // Basically skips the first element (command i.e. !speech) of given text
        return Arrays.stream(rawText.split(" ")).skip(1).collect(Collectors.joining());
    }

}
