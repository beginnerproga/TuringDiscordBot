package com.diagorn.turingbot.role;

import org.springframework.util.StringUtils;

/**
 * Custom server roles
 *
 * @author diagorn
 */
public enum ServerRoleEnum {

    FRONTEND("frontend"),
    JAVA("java"),
    ANALYTICS("analytics"),
    ANDROID("android"),
    SQL("sql");

    private final String name;

    ServerRoleEnum(String name) {
        this.name = name;
    }

    /**
     * Get custom role by name
     *
     * @param roleName - role name
     * @return ServerRoleEnum object if found, null if not
     */
    public static ServerRoleEnum parse(String roleName) {
        if (!StringUtils.hasText(roleName)) {
            return null;
        }

        for (ServerRoleEnum roleEnum : ServerRoleEnum.values()) {
            if (roleEnum.name.equalsIgnoreCase(roleName)) {
                return roleEnum;
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }
}
