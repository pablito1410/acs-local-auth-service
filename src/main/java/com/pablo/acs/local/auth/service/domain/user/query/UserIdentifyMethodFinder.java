package com.pablo.acs.local.auth.service.domain.user.query;

import com.pablo.acs.local.auth.service.domain.user.IdentifyMethodType;

import java.time.LocalDateTime;
import java.util.Collection;

public class UserIdentifyMethodFinder {

    private final UserIdentifyMethodFinderRepository repository;

    public UserIdentifyMethodFinder(final UserIdentifyMethodFinderRepository repository) {
        this.repository = repository;
    }

    public Collection<UserIdentifyMethodProjection> findAll() {
        return repository.findAll();
    }

    public Collection<UserIdentifyMethodProjection> findAll(final IdentifyMethodType identifyMethod) {
        return repository.findByIdentifyMethod(identifyMethod);
    }

    public IdentifiersWrapper findAll(final IdentifyMethodType identifyMethod,
                                      final LocalDateTime lastUpdate) {
        return new IdentifiersWrapper(
                repository.findByIdentifyMethodAndLastUpdate(identifyMethod, lastUpdate));
    }
}
