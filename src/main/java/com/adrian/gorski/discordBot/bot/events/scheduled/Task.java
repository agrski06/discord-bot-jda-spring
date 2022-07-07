package com.adrian.gorski.discordBot.bot.events.scheduled;

import lombok.Data;

// TODO: dynamic scheduled tasks
// https://riteshshergill.medium.com/dynamic-task-scheduling-with-spring-boot-6197e66fec42

// TODO: CRON to date/timestamp
// https://stackoverflow.com/questions/4363952/is-there-any-java-class-to-get-date-from-cron-expression

@Data
public class Task {
    private String cronExpression;
    private String name;
    private String data;
}
