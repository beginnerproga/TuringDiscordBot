package com.diagorn.turingbot.config;

import com.diagorn.turingbot.repo.RoleRegistry;
import com.diagorn.turingbot.role.ServerRole;
import com.diagorn.turingbot.role.ServerRoleEnum;
import org.javacord.api.entity.server.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Configures known server roles
 *
 * @author diagorn
 */
@Configuration
public class RoleConfig {
    private final Server mainServer;

    public RoleConfig(Server mainServer) {
        this.mainServer = mainServer;
    }

    @Bean
    public RoleRegistry roleRegistry() {
        Set<ServerRole> allRoles = mainServer.getRoles().stream()
                .map(role -> new ServerRole(role, ServerRoleEnum.parse(role.getName())))
                .collect(Collectors.toSet());
        return new RoleRegistry(allRoles);
    }
}
