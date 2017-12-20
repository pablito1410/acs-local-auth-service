package com.pablo.acs.local.auth.service.domain.user;

import java.util.Optional;

public interface IdentifyMethodRepository {

    Optional<IdentifyMethod> findById(Integer id);

}
