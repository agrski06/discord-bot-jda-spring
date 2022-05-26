package com.adrian.gorski.discordBot.events;

import discord4j.core.event.domain.message.MessageCreateEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageCreateListener extends MessageListener implements EventListener<MessageCreateEvent> {

    @Override
    public Class<MessageCreateEvent> getEventType() {
        return MessageCreateEvent.class;
    }

    @Override
    public Mono<Void> execute(MessageCreateEvent event) {
        String prefix = "!";
        if (event.getMessage().getContent().startsWith(prefix))
            return processCommand(event.getMessage());
        return Mono.empty();
    }
}
