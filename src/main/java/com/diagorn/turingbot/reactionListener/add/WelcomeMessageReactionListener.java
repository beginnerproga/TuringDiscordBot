package com.diagorn.turingbot.reactionListener.add;

import com.diagorn.turingbot.service.RoleService;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.emoji.Emoji;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.reaction.ReactionAddEvent;
import org.javacord.api.listener.message.reaction.ReactionAddListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

/**
 * Listener that handles welcome message reactions
 * If reaction is added on welcome message, a certain role is granted to user
 *
 * @author diagorn
 */
@Component
public class WelcomeMessageReactionListener implements ReactionAddListener {

    private final DiscordApi api;

    private final RoleService roleService;
    private final Message welcomeMessage;

    public WelcomeMessageReactionListener(DiscordApi discordApi, RoleService roleService, Message welcomeMessage) {
        this.api = discordApi;
        this.roleService = roleService;
        this.welcomeMessage = welcomeMessage;
    }

    @Override
    public void onReactionAdd(ReactionAddEvent event) {
        Message message = event.getMessage().get();
        if (!message.equals(welcomeMessage)) { //skip if reaction was added on a random message
            return;
        }

        try {
            User user = api.getUserById(event.getUserId()).get();
            Emoji emoji = event.getEmoji();

            roleService.addRoleToUserByEmoji(user, emoji);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
