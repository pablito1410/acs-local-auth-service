package com.pablo.acs.local.auth.service.domain.user;

import java.util.HashMap;
import java.util.Map;

public enum IdentifyMethodType {

    FINGERPRINT_SCANNER(1),
    CARD_READER(2),
    PIN(3);

    private static final Map<Integer, IdentifyMethodType> ENUM_MAP = new HashMap<>();
    static {
        for (IdentifyMethodType method : IdentifyMethodType.values()) {
            ENUM_MAP.put(method.getId(), method);
        }
    }
    private final int id;

    IdentifyMethodType(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static IdentifyMethodType getInstance(final int id) {
        return ENUM_MAP.get(id);
    }
}
