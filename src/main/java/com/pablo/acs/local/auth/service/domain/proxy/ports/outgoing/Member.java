package com.pablo.acs.local.auth.service.domain.proxy.ports.outgoing;

import java.util.Collection;

public class Member {

    private Collection<Attribute> attributes;
    private String id;
    private String type;

    private Member() { }

    public Collection<Attribute> getAttributes() {
        return attributes;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
