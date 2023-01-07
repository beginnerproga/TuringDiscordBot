package com.diagorn.turingbot.config;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.server.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutionException;

@Configuration
public class DiscordApiConfig {

    @Value("${bot.token}")
    private String botSecret;

    @Value("${server.id}")
    private long serverId;

    @Value("${welcome-message.id}")
    private long welcomeMessageId;

    @Value("${welcome-channel.id}")
    private long welcomeChannelId;

    @Bean
    public DiscordApi discordApi() {
        return new DiscordApiBuilder()
                .setToken(botSecret)
                .addIntents(Intent.MESSAGE_CONTENT)
                .login()
                .join();
    }

    @Bean
    public Server mainServer() {
        return discordApi()
                .getServerById(serverId)
                .orElseThrow(() -> new RuntimeException("Could not obtain server by it's id. ID: " + serverId));
    }

    @Bean
    public Message welcomeMessage() throws ExecutionException, InterruptedException {
        TextChannel welcomeMessageChannel = (TextChannel) mainServer().getChannelById(welcomeChannelId).get();
        return discordApi().getMessageById(welcomeMessageId, welcomeMessageChannel).get();
    }
}
