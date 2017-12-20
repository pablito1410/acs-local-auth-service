package com.pablo.acs.local.auth.service.infrastructure.repository;

import com.pablo.acs.local.auth.service.domain.user.IdentifyMethod;
import com.pablo.acs.local.auth.service.domain.user.IdentifyMethodRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentifyMethodJpaRepository
        extends JpaRepository<IdentifyMethod, Long>, IdentifyMethodRepository {
}
