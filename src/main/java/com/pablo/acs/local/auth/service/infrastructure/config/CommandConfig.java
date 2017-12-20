package com.pablo.acs.local.auth.service.infrastructure.config;

import com.pablo.acs.local.auth.service.infrastructure.command.CommandGateway;
import com.pablo.acs.local.auth.service.infrastructure.command.CommandHandlerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandConfig {

    @Bean
    CommandHandlerRegistry registry() {
        return new CommandHandlerRegistry();
    }

    @Bean
    public CommandGateway commandGateway(final CommandHandlerRegistry registry) {
        return new CommandGateway(registry);
    }

}
