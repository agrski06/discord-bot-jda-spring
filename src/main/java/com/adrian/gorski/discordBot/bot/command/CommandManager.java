package com.adrian.gorski.discordBot.bot.command;

import com.adrian.gorski.discordBot.bot.command.commands.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommandManager {
    private final List<Command> commands = new ArrayList<>();

    public CommandManager() {
        //Commands which do not take arguments
        addCommand(new PingCommand());
        addCommand(new ConnectCommand());
        addCommand(new DisconnectCommand());
        addCommand(new QueueCommand());
        addCommand(new ShuffleCommand());

        // Commands which take arguments
        addCommand(new EchoCommand());
        addCommand(new SoundCommand());
        addCommand(new SpeechCommand());
        addCommand(new PlayCommand());
    }

    private void addCommand(Command command) {
        if (!commands.isEmpty()) {
            boolean nameFound = commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(command.getName()));
            if (nameFound) {
                throw new IllegalArgumentException("A command with this name is already present");
            }
        }
        commands.add(command);
    }

    @Nullable
    private Command getCommand(String command) {
        for (Command cmd : this.commands) {
            if (cmd.getAliases().contains(command.toLowerCase())) {
                return cmd;
            }
        }
        return null;
    }

    public void handle(MessageReceivedEvent event) {
        String text = event.getMessage().getContentRaw();
        if (text.charAt(0) != '!') return;

        String commandText = text.substring(1).split(" ")[0];
        Command command = getCommand(commandText);

        if (command != null) {
            if (command.doesTakeArgs()) {
                // set arguments if command takes any
                String args = Arrays.stream(text.split(" ")).skip(1).collect(Collectors.joining(" "));
                command.setArgs(args);
            }
            command.handle(event);
        } else {
            event.getChannel().sendMessage("No such command").queue();
        }
    }

    public List<Command> getCommands() {
        return commands;
    }
}
