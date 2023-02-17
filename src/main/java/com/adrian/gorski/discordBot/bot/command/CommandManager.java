package com.adrian.gorski.discordBot.bot.command;

import com.adrian.gorski.discordBot.api.sounds.SoundsService;
import com.adrian.gorski.discordBot.bot.command.commands.*;
import com.adrian.gorski.discordBot.bot.config.Bot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommandManager {
    private final List<Command> commands = new ArrayList<>();

    public CommandManager(SoundsService soundsService) {
        //Commands which do NOT take arguments
        addCommand(new PingCommand());
        addCommand(new ConnectCommand());
        addCommand(new DisconnectCommand());
        addCommand(new QueueCommand());
        addCommand(new ShuffleCommand());
        addCommand(new ClearCommand());

        // Commands which take arguments
        addCommand(new EchoCommand());
        addCommand(new SoundCommand(soundsService));
        addCommand(new SpeechCommand());
        addCommand(new PlayCommand());
        addCommand(new HelpCommand());
        addCommand(new BassCommand());
        addCommand(new SkipCommand());

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
        if (!text.startsWith(Bot.prefix)) return;

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

    // Inner class - help command, requires access to commands
    private class HelpCommand extends Command {

        public HelpCommand() {
            aliases = List.of("help");
        }

        @Override
        public String getName() {
            return "help";
        }

        @Override
        public String getHelp() {
            return "Displays all the commands";
        }

        @Override
        public void handle(MessageReceivedEvent event) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("Commands");
            embedBuilder.addField("Prefix: " + Bot.prefix, "\n", false);
            for (Command command : getCommands()) {
                embedBuilder.addField(command.getName(),
                        "[" + String.join(", ", command.getAliases()) + "] "
                                + (command.doesTakeArgs() ? "" : "\n") + command.getHelp(),
                        !command.doesTakeArgs());
            }
            event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
        }
    }

}
