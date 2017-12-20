package com.pablo.acs.local.auth.service.infrastructure.command;

import com.pablo.acs.local.auth.service.domain.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CommandHandlerRegistry {

    private final Map<Class<? extends Command>, CommandHandler<? extends Command>> handlers = new HashMap<>();

    public <C extends Command> void register(final Class<C> command, final CommandHandler<C> handler) {
        Objects.requireNonNull(command, "command cannot be null");
        Objects.requireNonNull(handler, "handler cannot be null");
        handlers.put(command, handler);
    }

    public <C extends Command> CommandHandler<C> get(final C command) {
        final CommandHandler<C> commandHandler = (CommandHandler<C>) handlers.get(command.getClass());

        if (commandHandler == null) {
            throw new IllegalStateException("no handler registered for command: " + command.getClass().getSimpleName());
        }

        return commandHandler;
    }

}
