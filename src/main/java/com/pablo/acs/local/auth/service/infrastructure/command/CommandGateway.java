package com.pablo.acs.local.auth.service.infrastructure.command;

import com.pablo.acs.local.auth.service.domain.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class CommandGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandGateway.class);

    private final CommandHandlerRegistry registry;

    public CommandGateway(final CommandHandlerRegistry registry) {
        this.registry = registry;
    }

    public void dispatch(final Command command) {
        Objects.requireNonNull(command, "command cannot be null");

        final CommandHandler<Command> commandHandler = registry.get(command);

        LOGGER.info("Dispatching command {} by commandHandler {}", command, commandHandler);

        commandHandler.handle(command);

        LOGGER.info("Command {} dispatched by commandHandler {}", command, commandHandler   );
    }
}
