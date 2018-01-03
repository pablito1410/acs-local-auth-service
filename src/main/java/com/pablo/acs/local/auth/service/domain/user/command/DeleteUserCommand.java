package com.pablo.acs.local.auth.service.domain.user.command;

import com.pablo.acs.local.auth.service.domain.command.Command;

public class DeleteUserCommand implements Command {

    private final Long id;

    public DeleteUserCommand(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
