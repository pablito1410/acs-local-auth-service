package com.pablo.acs.local.auth.service.domain.user.query.projection;

public class UserIdentifyMethodProjection {

    private Long userId;
    private Integer identificationMethodId;
    private byte[] identifier;

    private UserIdentifyMethodProjection() { }

    public UserIdentifyMethodProjection(final Long userId,
                                        final Integer identificationMethodId,
                                        final byte[] identifier) {
        this.userId = userId;
        this.identificationMethodId = identificationMethodId;
        this.identifier = identifier;
    }

    public Long getUserId() {
        return userId;
    }

    public byte[] getIdentifier() {
        return identifier;
    }

    public Integer getIdentificationMethodId() { return identificationMethodId; }

    @Override
    public String toString() {
        return "UserIdentifyMethodProjection{" +
                "userId=" + userId +
                ", identificationMethodId=" + identificationMethodId +
                ", identifier=" + identifier +
                '}';
    }
}
