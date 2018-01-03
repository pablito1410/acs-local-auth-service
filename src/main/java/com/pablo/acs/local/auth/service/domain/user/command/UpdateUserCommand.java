package com.pablo.acs.local.auth.service.domain.user.command;

import com.pablo.acs.local.auth.service.domain.command.Command;

public class UpdateUserCommand implements Command {

    private final String externalId;
    private final String name;

    public UpdateUserCommand(final String externalId, final String name) {
        this.externalId = externalId;
        this.name = name;
    }

    public String getExternalId() {
        return externalId;
    }

    public String getName() {
        return name;
    }
}
