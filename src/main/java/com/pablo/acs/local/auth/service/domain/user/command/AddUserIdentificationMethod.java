package com.pablo.acs.local.auth.service.domain.user.command;

import com.pablo.acs.local.auth.service.domain.command.Command;

public class AddUserIdentificationMethod implements Command {
    private Long userId;
    private Integer identificationMethodId;
    private byte[] identifier;

    public Long getUserId() {
        return userId;
    }

    public Integer getIdentificationMethodId() {
        return identificationMethodId;
    }

    public byte[] getIdentifier() {
        return identifier;
    }
}
