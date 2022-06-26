package com.adrian.gorski.discordBot.api.error;

import org.springframework.stereotype.Service;

@Service
public class ErrorService {
    public String error() {
        return "There was an error...";
    }
}
