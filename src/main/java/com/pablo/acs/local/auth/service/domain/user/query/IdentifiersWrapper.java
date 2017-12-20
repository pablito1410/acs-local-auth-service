package com.pablo.acs.local.auth.service.domain.user.query;

import java.util.Collection;

public class IdentifiersWrapper {

    private final Collection<UserIdentifyMethodProjection> identifiers;

    public IdentifiersWrapper(final Collection<UserIdentifyMethodProjection> identifiers) {
        this.identifiers = identifiers;
    }

    public Collection<UserIdentifyMethodProjection> getIdentifiers() {
        return identifiers;
    }
}
