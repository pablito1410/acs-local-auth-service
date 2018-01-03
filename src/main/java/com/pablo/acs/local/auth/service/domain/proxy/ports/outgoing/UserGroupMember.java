package com.pablo.acs.local.auth.service.domain.proxy.ports.outgoing;

import java.util.Collection;

public class UserGroupMember {

    private Collection<Attribute> attributes;
    private Member to;

    private UserGroupMember() { }

    public Collection<Attribute> getAttributes() {
        return attributes;
    }

    public Member getTo() {
        return to;
    }
}
