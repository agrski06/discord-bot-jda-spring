package com.adrian.gorski.discordBot.bot.events.scheduled;

// TODO: task manager, adding tasks from chat/dashboard

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ScheduledTaskManager {
    
    private final List<Task> tasks;

    public void addTask(Task task) {
        this.tasks.add(task);
    }
    
}
