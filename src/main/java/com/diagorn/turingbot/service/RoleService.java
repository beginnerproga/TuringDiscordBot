package com.diagorn.turingbot.service;

import com.diagorn.turingbot.role.ServerRole;
import org.javacord.api.entity.emoji.Emoji;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.user.User;

/**
 * Roles business logic
 *
 * @author diagorn
 */
public interface RoleService {

    /**
     * Adds role to user if user and role are valid
     * @param user - user
     * @param role - role
     */
    void addRoleToUser(User user, Role role);

    /**
     * Adds role to user if user and role are valid
     * @param user - user
     * @param role - role
     */
    void addRoleToUser(User user, ServerRole role);

    /**
     * Adds role to user by emoji.
     * If the emoji is connected to a role, adds that role. Else does nothing
     * @param user - user
     * @param emoji - emoji
     */
    void addRoleToUserByEmoji(User user, Emoji emoji);

    /**
     * Removes role from user if user and role are valid
     * @param user - user
     * @param role - role
     */
    void removeRoleFromUser(User user, Role role);

    /**
     * Removes role from user if user and role are valid
     * @param user - user
     * @param role - role
     */
    void removeRoleFromUser(User user, ServerRole role);

    /**
     * Removes role from user by emoji.
     * If the emoji is connected to a role, removes that role. Else does nothing
     * @param user - user
     * @param emoji - emoji
     */
    void removeRoleFromUserByEmoji(User user, Emoji emoji);
}
