package com.diagorn.turingbot.repo;

import com.diagorn.turingbot.role.ServerRole;
import com.diagorn.turingbot.role.ServerRoleEnum;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Registry containing all roles of the server
 *
 * @author diagorn
 */
public class RoleRegistry {
    /**
     * All roles container
     */
    private final Set<ServerRole> roles;

    public RoleRegistry(Set<ServerRole> roles) {
        this.roles = roles;
    }

    /**
     * @return all server roles
     */
    public Set<ServerRole> getAllRoles() {
        return new HashSet<>(roles);
    }

    /**
     * @return all custom roles (known to this application)
     */
    public Set<ServerRole> getCustomRoles() {
        return roles.stream()
                .filter(ServerRole::isCustom)
                .collect(Collectors.toSet());
    }

    /**
     * Find a custom role by ite enum
     *
     * @param roleEnum - enum role representation
     * @return custom role if such is found, null if not
     */
    public ServerRole getCustomRoleByEnum(ServerRoleEnum roleEnum) {
        if (roleEnum == null) {
            return null;
        }

        for (ServerRole serverRole : getCustomRoles()) {
            if (serverRole.getRoleEnum().equals(roleEnum)) {
                return serverRole;
            }
        }

        return null;
    }
}
