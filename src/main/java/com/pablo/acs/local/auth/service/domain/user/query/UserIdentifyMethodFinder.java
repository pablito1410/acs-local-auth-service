package com.pablo.acs.local.auth.service.domain.user.query;

import com.pablo.acs.local.auth.service.domain.user.IdentifyMethodType;
import com.pablo.acs.local.auth.service.domain.user.query.projection.IdentifiersWrapper;
import com.pablo.acs.local.auth.service.domain.user.query.projection.UserIdentifyMethodProjection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Collection;

public class UserIdentifyMethodFinder {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserIdentifyMethodFinder.class);

    private final UserIdentifyMethodFinderRepository repository;

    public UserIdentifyMethodFinder(final UserIdentifyMethodFinderRepository repository) {
        this.repository = repository;
    }

    public Collection<UserIdentifyMethodProjection> findAll() {
        LOGGER.info("Searching identifiers...");
        return repository.findAll();
    }

    public Collection<UserIdentifyMethodProjection> findAll(final IdentifyMethodType identifyMethod) {
        LOGGER.info("Searching identifiers... identifyMethod={}", identifyMethod);
        return repository.findByIdentifyMethod(identifyMethod);
    }

    public IdentifiersWrapper findAll(final IdentifyMethodType identifyMethod,
                                      final LocalDateTime lastUpdate) {
        LOGGER.info("Searching identifiers... identifyMethod={} lastUpdate={}", identifyMethod, lastUpdate);
        return new IdentifiersWrapper(
                repository.findByIdentifyMethodAndLastUpdate(identifyMethod, lastUpdate));
    }

    public IdentifiersWrapper findAll(final LocalDateTime lastUpdate) {
        LOGGER.info("Searching identifiers... lastUpdate={}", lastUpdate);
        return new IdentifiersWrapper(repository.findByLastUpdate(lastUpdate));
    }
}
