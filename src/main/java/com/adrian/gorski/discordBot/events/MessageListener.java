package com.adrian.gorski.discordBot.events;

import com.adrian.gorski.discordBot.events.commands.map.CommandMap;
import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public abstract class MessageListener {

    private final String prefix = "!";

    private final Map<String, String> commands = new HashMap<>();

    public MessageListener() {
        CommandMap commandMap = new CommandMap();
        commands.putAll(commandMap.getCommands());
    }

    public Mono<Void> processCommand(Message eventMessage) {
        String command = eventMessage.getContent().substring(1).strip();
        if (!command.contains(" ")) {
            return Mono.just(eventMessage)
                    .filter(message -> message.getContent().startsWith(prefix))
                    .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                    .filter(message -> commands.containsKey(command))
                    .flatMap(Message::getChannel)
                    .flatMap(channel -> channel.createMessage(commands.get(command)))
                    .then();
        }
        String[] args = command.split(" ");
        return Mono.just(eventMessage)
                .filter(message -> message.getContent().startsWith(prefix))
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .filter(message -> commands.containsKey(args[0]))
                .flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage(commands.get(args[0])))
                .then();
    }
}
