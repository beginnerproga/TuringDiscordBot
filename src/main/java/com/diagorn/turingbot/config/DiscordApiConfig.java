package com.diagorn.turingbot.config;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscordApiConfig {

    @Value("${bot.token}")
    private String botSecret;

    @Bean
    public DiscordApi discordApi() {
        return new DiscordApiBuilder()
                .setToken(botSecret)
                .addIntents(Intent.MESSAGE_CONTENT)
                .login()
                .join();
    }
}
