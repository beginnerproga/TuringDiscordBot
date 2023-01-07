package com.diagorn.turingbot.reactionListener.remove;

import com.diagorn.turingbot.service.RoleService;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.emoji.Emoji;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.reaction.ReactionRemoveEvent;
import org.javacord.api.listener.message.reaction.ReactionRemoveListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

/**
 * Listener that handles welcome message reactions
 * If reaction is removed from welcome message, a certain role is removed from user
 *
 * @author diagorn
 */
@Component
public class WelcomeMessageReactionRemoveListener implements ReactionRemoveListener {

    private final DiscordApi api;

    private final RoleService roleService;
    private final Message welcomeMessage;

    public WelcomeMessageReactionRemoveListener(DiscordApi api, RoleService roleService, Message welcomeMessage) {
        this.api = api;
        this.roleService = roleService;
        this.welcomeMessage = welcomeMessage;
    }

    @Override
    public void onReactionRemove(ReactionRemoveEvent event) {
        Message message = event.getMessage().get();
        if (!message.equals(welcomeMessage)) { //skip if reaction was added on a random message
            return;
        }

        try {
            User user = api.getUserById(event.getUserId()).get();
            Emoji emoji = event.getEmoji();

            roleService.removeRoleFromUserByEmoji(user, emoji);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
