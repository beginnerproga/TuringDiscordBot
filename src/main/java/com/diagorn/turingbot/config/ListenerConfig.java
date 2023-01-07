package com.diagorn.turingbot.config;

import com.diagorn.turingbot.reactionListener.add.WelcomeMessageReactionListener;
import com.diagorn.turingbot.reactionListener.remove.WelcomeMessageReactionRemoveListener;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.server.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutionException;

/**
 * Configures listeners - adds all listeners to API
 *
 * @author diagorn
 */
@Configuration
public class ListenerConfig {

    private final DiscordApi api;

    private final WelcomeMessageReactionListener welcomeMessageReactionListener;
    private final WelcomeMessageReactionRemoveListener welcomeMessageReactionRemoveListener;

    public ListenerConfig(DiscordApi api,
                          WelcomeMessageReactionListener welcomeMessageReactionListener,
                          WelcomeMessageReactionRemoveListener welcomeMessageReactionRemoveListener) {
        this.api = api;
        this.welcomeMessageReactionListener = welcomeMessageReactionListener;
        this.welcomeMessageReactionRemoveListener = welcomeMessageReactionRemoveListener;
    }

    @PostConstruct
    public void setListeners() {
        api.addListener(welcomeMessageReactionListener);
        api.addListener(welcomeMessageReactionRemoveListener);
    }
}
