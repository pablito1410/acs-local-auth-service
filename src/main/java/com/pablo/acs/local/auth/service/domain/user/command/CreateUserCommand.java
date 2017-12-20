package com.pablo.acs.local.auth.service.domain.user.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pablo.acs.local.auth.service.domain.command.Command;

public class CreateUserCommand implements Command {

    private final String name;
    private final byte[] photo;
    private String externalId;

    @JsonCreator
    public CreateUserCommand(@JsonProperty("name") final String name,
                      @JsonProperty("photo") final byte[] photo,
                             @JsonProperty("externalId") final String externalId) {
        this.name = name;
        this.photo = photo;
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public String getExternalId() {
        return externalId;
    }
}
