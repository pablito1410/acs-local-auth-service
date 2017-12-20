package com.pablo.acs.local.auth.service.infrastructure.repository;

import com.pablo.acs.local.auth.service.domain.user.UserIdentificationMethod;
import com.pablo.acs.local.auth.service.domain.user.UserIdentificationMethodRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserIdentificationMethodJpaRepository
        extends JpaRepository<UserIdentificationMethod, UserIdentificationMethod.UserIdentificationMethodId>,
        UserIdentificationMethodRepository {
}
