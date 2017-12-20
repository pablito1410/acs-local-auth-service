package com.pablo.acs.local.auth.service.domain.user.query;

public class UserIdentifyMethodProjection {

    private Long userId;
    private byte[] identifier;

    private UserIdentifyMethodProjection() { }

    public UserIdentifyMethodProjection(final Long userId, final byte[] identifier) {
        this.userId = userId;
        this.identifier = identifier;
    }

    public Long getUserId() {
        return userId;
    }

    public byte[] getIdentifier() {
        return identifier;
    }
}
