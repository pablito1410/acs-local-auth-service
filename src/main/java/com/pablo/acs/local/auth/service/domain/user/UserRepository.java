package com.pablo.acs.local.auth.service.domain.user;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByExternalId(String userExternalId);

    Optional<User> findById(Long id);
}
