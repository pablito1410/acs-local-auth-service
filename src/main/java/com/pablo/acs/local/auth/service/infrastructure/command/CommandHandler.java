package com.pablo.acs.local.auth.service.infrastructure.command;

@FunctionalInterface
public interface CommandHandler<C> {

    void handle(C command);
}
