package com.adrian.gorski.discordBot.bot.command;

import com.adrian.gorski.discordBot.bot.command.commands.*;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private final List<SimpleCommand> simpleCommands = new ArrayList<>();
    private final List<CommandWithArgs> commandsWithArgs = new ArrayList<>();

    public CommandManager() {
        addSimpleCommand(new PingCommand());
        addSimpleCommand(new ConnectCommand());
        addSimpleCommand(new DisconnectCommand());

        addCommandWithArgs(new EchoCommand());
        addCommandWithArgs(new SoundCommand());
        addCommandWithArgs(new SpeechCommand());
        addCommandWithArgs(new PlayCommand());
    }

    private void addSimpleCommand(SimpleCommand command) {
        if (!simpleCommands.isEmpty()) {
            boolean nameFound = simpleCommands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(command.getName()));
            if (nameFound) {
                throw new IllegalArgumentException("A command with this name is already present");
            }
        }
        simpleCommands.add(command);
    }

    private void addCommandWithArgs(CommandWithArgs command) {
        if (!commandsWithArgs.isEmpty()) {
            boolean nameFound = commandsWithArgs.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(command.getName()));
            if (nameFound) {
                throw new IllegalArgumentException("A command with this name is already present");
            }
        }
        commandsWithArgs.add(command);
    }

    @Nullable
    private SimpleCommand getSimpleCommand(String command) {
        String search = command.toLowerCase();
        for (SimpleCommand cmd : this.simpleCommands) {
            if (cmd.getAliases().contains(search)) {
                return cmd;
            }
        }
        return null;
    }

    @Nullable
    private CommandWithArgs getCommandWithArgs(String command) {
        String search = command.toLowerCase();
        for (CommandWithArgs cmd : this.commandsWithArgs) {
            if (cmd.getAliases().contains(search)) {
                return cmd;
            }
        }
        return null;
    }

    public void handle(MessageReceivedEvent event) {
        Message message = event.getMessage();
        if (message.getContentRaw().charAt(0) != '!') return;

        String command;
        String params;

        if (message.getContentRaw().contains(" ")) {
            command = message.getContentRaw().substring(1, message.getContentRaw().indexOf(" "));
            params = message.getContentRaw().substring(command.length()+1).strip();

            CommandWithArgs cmd = getCommandWithArgs(command);
            if (cmd != null) cmd.handle(event, params);
            else event.getTextChannel().sendMessage("No such command").queue();
        } else {
            command = message.getContentRaw().substring(1);

            SimpleCommand cmd = getSimpleCommand(command);
            if (cmd != null) cmd.handle(event);
            else event.getTextChannel().sendMessage("No such command").queue();
        }

    }

}
