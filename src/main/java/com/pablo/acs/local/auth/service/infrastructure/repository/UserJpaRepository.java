package com.pablo.acs.local.auth.service.infrastructure.repository;

import com.pablo.acs.local.auth.service.domain.user.User;
import com.pablo.acs.local.auth.service.domain.user.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long>, UserRepository {
}
