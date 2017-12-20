package com.pablo.acs.local.auth.service.infrastructure.config;

import com.pablo.acs.local.auth.service.domain.user.UserService;
import com.pablo.acs.local.auth.service.domain.user.command.AddUserIdentificationMethod;
import com.pablo.acs.local.auth.service.domain.user.command.CreateUserCommand;
import com.pablo.acs.local.auth.service.infrastructure.command.CommandHandlerRegistry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserCommandConfig {

    @Bean
    InitializingBean initializeUserCommands(final CommandHandlerRegistry registry,
                                            final UserService userService) {
        return () -> {
            registry.register(CreateUserCommand.class, userService::handle);
            registry.register(AddUserIdentificationMethod.class, userService::handle);
        };
    }
}
