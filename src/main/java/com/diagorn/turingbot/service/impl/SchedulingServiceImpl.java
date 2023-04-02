package com.diagorn.turingbot.service.impl;

import com.diagorn.turingbot.client.SchedulingClient;
import com.diagorn.turingbot.domain.Chapter;
import com.diagorn.turingbot.service.SchedulingService;
import lombok.SneakyThrows;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulingServiceImpl implements SchedulingService {
    private final SchedulingClient schedulingClient;
    private final DiscordApi discordApi;
    @Value("${scheduling-channel-id}")
    private String channelId;

    @Autowired
    public SchedulingServiceImpl(SchedulingClient schedulingClient, DiscordApi discordApi) {
        this.discordApi = discordApi;
        this.schedulingClient = schedulingClient;
    }

    @Override
    @Scheduled(cron = "${scheduling.interval-in-cron.format}")
    public void schedulingMethod() {
        int counterOfTasks = 0;
        Chapter[] chapters = Chapter.values();
        for (Chapter chapter : chapters) {
            List<Integer> headIds = schedulingClient.getIds(chapter.getName());
            if (counterOfTasks <= headIds.size()) {
                sendAlgorithmTask(headIds.get(counterOfTasks));
                ++counterOfTasks;
            } else {
                counterOfTasks = 0;
            }
        }

    }

    @SneakyThrows
    @Override
    public void sendAlgorithmTask(int taskId) {
        String result = schedulingClient.getAlgorithm(taskId);
        new MessageBuilder()
                .append(result)
                .send(discordApi.getTextChannelById(channelId).orElseThrow(() -> {
                    throw new IllegalStateException("Wrong channel id for task messages");
                }));
    }


}
