package com.pablo.acs.local.auth.service.domain.user;

public enum IdentifyMethodType {

    FINGERPRINT_SCANNER(1),
    CARD_READER(2)
    ;

    private final int id;

    IdentifyMethodType(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
