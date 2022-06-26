package com.adrian.gorski.discordBot.bot.command;

import java.util.List;

public abstract class Command {

    /**
     * @return name of the command
     */
    public abstract String getName();

    /**
     * @return help in String format
     */
    public abstract String getHelp();

    public abstract List<String> getAliases();

}
