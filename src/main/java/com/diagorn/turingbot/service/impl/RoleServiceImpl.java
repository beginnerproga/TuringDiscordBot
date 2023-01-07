package com.diagorn.turingbot.service.impl;

import com.diagorn.turingbot.emoji.ServerEmoji;
import com.diagorn.turingbot.repo.EmojiRegistry;
import com.diagorn.turingbot.repo.RoleRegistry;
import com.diagorn.turingbot.role.ServerRole;
import com.diagorn.turingbot.role.ServerRoleEnum;
import com.diagorn.turingbot.service.RoleService;
import org.javacord.api.entity.emoji.Emoji;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.user.User;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRegistry roleRegistry;
    private final EmojiRegistry emojiRegistry;

    public RoleServiceImpl(RoleRegistry roleRegistry, EmojiRegistry emojiRegistry) {
        this.roleRegistry = roleRegistry;
        this.emojiRegistry = emojiRegistry;
    }

    @Override
    public void addRoleToUser(User user, Role role) {
        if (role != null && user != null) {
            user.addRole(role);
        }
    }

    @Override
    public void addRoleToUser(User user, ServerRole role) {
        addRoleToUser(user, role.getRole());
    }

    @Override
    public void addRoleToUserByEmoji(User user, Emoji emoji) {
        ServerEmoji serverEmoji = emojiRegistry.findCustomEmoji(emoji);
        if (serverEmoji == null || serverEmoji.getEmojiEnum() == null) {
            return;
        }

        switch (serverEmoji.getEmojiEnum()) {
            case JAVA:
                addRoleToUser(user, roleRegistry.getCustomRoleByEnum(ServerRoleEnum.JAVA));
                break;
            case SQL:
                addRoleToUser(user, roleRegistry.getCustomRoleByEnum(ServerRoleEnum.SQL));
                break;
            case FRONTEND:
                addRoleToUser(user, roleRegistry.getCustomRoleByEnum(ServerRoleEnum.FRONTEND));
                break;
            case ANDROID:
                addRoleToUser(user, roleRegistry.getCustomRoleByEnum(ServerRoleEnum.ANDROID));
                break;
            case ANALYTICS:
                addRoleToUser(user, roleRegistry.getCustomRoleByEnum(ServerRoleEnum.ANALYTICS));
                break;
        }
    }

    @Override
    public void removeRoleFromUser(User user, Role role) {
        if (user != null && role != null) {
            user.removeRole(role);
        }
    }

    @Override
    public void removeRoleFromUser(User user, ServerRole role) {
        removeRoleFromUser(user, role.getRole());
    }

    @Override
    public void removeRoleFromUserByEmoji(User user, Emoji emoji) {
        ServerEmoji serverEmoji = emojiRegistry.findCustomEmoji(emoji);
        if (serverEmoji == null || serverEmoji.getEmojiEnum() == null) {
            return;
        }

        switch (serverEmoji.getEmojiEnum()) {
            case JAVA:
                removeRoleFromUser(user, roleRegistry.getCustomRoleByEnum(ServerRoleEnum.JAVA));
                break;
            case SQL:
                removeRoleFromUser(user, roleRegistry.getCustomRoleByEnum(ServerRoleEnum.SQL));
                break;
            case FRONTEND:
                removeRoleFromUser(user, roleRegistry.getCustomRoleByEnum(ServerRoleEnum.FRONTEND));
                break;
            case ANDROID:
                removeRoleFromUser(user, roleRegistry.getCustomRoleByEnum(ServerRoleEnum.ANDROID));
                break;
            case ANALYTICS:
                removeRoleFromUser(user, roleRegistry.getCustomRoleByEnum(ServerRoleEnum.ANALYTICS));
                break;
        }
    }
}
