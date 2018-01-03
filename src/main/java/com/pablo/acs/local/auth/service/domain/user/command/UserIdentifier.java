package com.pablo.acs.local.auth.service.domain.user.command;

public class UserIdentifier {
    private Long userId;
    private Integer identificationMethodId;
    private byte[] identifier;
    private String pin;

    private UserIdentifier() { }

    public Long getUserId() {
        return userId;
    }

    public Integer getIdentificationMethodId() {
        return identificationMethodId;
    }

    public byte[] getIdentifier() {
        return identifier;
    }

    public String getPin() {
        return pin;
    }

    @Override
    public String toString() {
        return "UserIdentifier{" +
                "userId=" + userId +
                ", identificationMethodId=" + identificationMethodId +
                '}';
    }
}
