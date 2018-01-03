package com.pablo.acs.local.auth.service.domain.proxy.ports.outgoing;

import java.util.Collection;

public class UserGroup {

    private Collection<Attribute> attributes;
    private String id;
    private String name;
    private String type;

    private UserGroup() { }

    public String getId() {
        return id;
    }

    public Collection<Attribute> getAttributes() {
        return attributes;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
