package com.adrian.gorski.discordBot.events;

import com.adrian.gorski.discordBot.events.commands.map.CommandMap;
import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

import java.util.Arrays;

public abstract class MessageListener {

    private final CommandMap commands = new CommandMap();

    public Mono<Void> processCommand(Message eventMessage) {
        String command = eventMessage.getContent().substring(1).strip();
        String[] args = Arrays.stream(command.split(" ")).skip(1).toArray(String[]::new);
        String finalCommand = command.substring(0, command.indexOf(' '));

        System.out.println(command + " || " + Arrays.toString(args));
        return Mono.just(eventMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .filter(message -> commands.containsKey(finalCommand))
                .flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage(commands.get(finalCommand)))
                .then();
    }
}
