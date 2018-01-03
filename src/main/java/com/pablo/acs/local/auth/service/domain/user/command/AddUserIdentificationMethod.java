package com.pablo.acs.local.auth.service.domain.user.command;

import com.pablo.acs.local.auth.service.domain.command.Command;

import java.util.Collection;

public class AddUserIdentificationMethod implements Command {

    private Collection<UserIdentifier> identifiers;

    private AddUserIdentificationMethod() { }

    public AddUserIdentificationMethod(final Collection<UserIdentifier> identifiers) {
        this.identifiers = identifiers;
    }

    public Collection<UserIdentifier> getIdentifiers() {
        return identifiers;
    }

    @Override
    public String toString() {
        return "AddUserIdentificationMethod{" +
                "identifiers=" + identifiers +
                '}';
    }
}
