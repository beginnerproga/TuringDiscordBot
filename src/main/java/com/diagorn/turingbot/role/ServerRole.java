package com.diagorn.turingbot.role;

import org.javacord.api.entity.permission.Role;

import java.util.Objects;

/**
 * Server role
 *
 * @author diagorn
 */
public class ServerRole {
    /**
     * Javacord role object
     */
    private Role role;
    /**
     * Custom role
     */
    private ServerRoleEnum roleEnum;

    public ServerRole(Role role, ServerRoleEnum roleEnum) {
        this.role = role;
        this.roleEnum = roleEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerRole that = (ServerRole) o;
        return role.equals(that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role);
    }

    public boolean isCustom() {
        return roleEnum != null;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public ServerRoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(ServerRoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }
}
