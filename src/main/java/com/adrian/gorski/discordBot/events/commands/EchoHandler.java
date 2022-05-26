package com.adrian.gorski.discordBot.events.commands;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EchoHandler extends Command {

    public EchoHandler() {
        super("echo");
    }

    @Override
    public String generateResponse() {
        String com = this.getCommand();
//        String str = Arrays.stream(com.split(" ")).skip(1).collect(Collectors.joining(" "));
        System.out.println(com);
        return com;
    }
}
