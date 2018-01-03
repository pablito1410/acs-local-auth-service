package com.pablo.acs.local.auth.service.domain.user.query.projection;

import java.util.Collection;

public class IdentifiersWrapper {

    private final Collection<UserIdentifyMethodProjection> identifiers;

    public IdentifiersWrapper(final Collection<UserIdentifyMethodProjection> identifiers) {
        this.identifiers = identifiers;
    }

    public Collection<UserIdentifyMethodProjection> getIdentifiers() {
        return identifiers;
    }

    @Override
    public String toString() {
        return "IdentifiersWrapper{" +
                "identifiers=" + identifiers +
                '}';
    }
}
