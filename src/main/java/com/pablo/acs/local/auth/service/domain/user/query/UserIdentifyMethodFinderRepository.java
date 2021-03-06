package com.pablo.acs.local.auth.service.domain.user.query;

import com.pablo.acs.local.auth.service.domain.user.IdentifyMethodType;
import com.pablo.acs.local.auth.service.domain.user.query.projection.UserIdentifyMethodProjection;

import java.time.LocalDateTime;
import java.util.Collection;

public interface UserIdentifyMethodFinderRepository {

    Collection<UserIdentifyMethodProjection> findByIdentifyMethod(IdentifyMethodType identifyMethod);

    Collection<UserIdentifyMethodProjection> findByIdentifyMethodAndLastUpdate(IdentifyMethodType identifyMethod,
                                                                               LocalDateTime lastUpdate);

    Collection<UserIdentifyMethodProjection> findAll();

    Collection<UserIdentifyMethodProjection> findByLastUpdate(final LocalDateTime lastUpdate);
}
