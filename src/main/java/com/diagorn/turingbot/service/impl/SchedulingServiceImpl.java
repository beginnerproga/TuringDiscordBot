package com.diagorn.turingbot.service.impl;

import com.diagorn.turingbot.client.SchedulingClient;
import com.diagorn.turingbot.service.SchedulingService;
import com.diagorn.turingbot.util.Chapters;
import lombok.SneakyThrows;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.mention.AllowedMentions;
import org.javacord.api.entity.message.mention.AllowedMentionsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@PropertySource("classpath:application-scheduling.properties")
public class SchedulingServiceImpl implements SchedulingService {
    private final SchedulingClient schedulingClient;
    private final DiscordApi discordApi;
    Chapters[] chapters = Chapters.values();
    @Value("${scheduling-channel-id}")
    private String channelId;
    private int counterOfTasks = 0;
    private int counterOfChapters = 0;


    @Autowired
    public SchedulingServiceImpl(SchedulingClient schedulingClient, DiscordApi discordApi) {
        this.discordApi = discordApi;
        this.schedulingClient = schedulingClient;
    }

    @Override
    @Scheduled(cron = "${interval-in-cron}")
    public void schedulingMethod() {
        Chapters chapter = Chapters.BEGINNER_TASKS;
        while (true) {
            List<Integer> beginnerIds = schedulingClient.getIds(chapter.getName());
            if (counterOfTasks < beginnerIds.size()) {
                sendAlgorithmTask(beginnerIds.get(counterOfTasks));
                ++counterOfTasks;
            } else {
                ++counterOfChapters;
                if (counterOfChapters == chapters.length)
                    break;
                counterOfTasks = 0;
                chapter = chapters[counterOfChapters];
            }
        }
        sendEmptyTasksMessage();
    }

    @SneakyThrows
    @Override
    public void sendAlgorithmTask(int taskId) {
        AllowedMentions allowedMentions = new AllowedMentionsBuilder()
                .setMentionRoles(true)
                .setMentionEveryoneAndHere(true)
                .build();
        String result = schedulingClient.getAlgorithm(taskId);
        new MessageBuilder()
                .setAllowedMentions(allowedMentions)
                .append(result)
                .send(discordApi.getTextChannelById(channelId).orElseThrow(() -> {
                    throw new RuntimeException();
                }));
    }

    @Override
    @SneakyThrows
    public void sendEmptyTasksMessage() {
        AllowedMentions allowedMentions = new AllowedMentionsBuilder()
                .setMentionRoles(true)
                .setMentionEveryoneAndHere(true)
                .build();
        new MessageBuilder()
                .setAllowedMentions(allowedMentions)
                .append("Задач на сайте больше нет, обновите сайт для получения новых задач.")
                .send(discordApi.getTextChannelById(channelId).orElseThrow(() -> {
                    throw new RuntimeException();
                }));

    }

}
