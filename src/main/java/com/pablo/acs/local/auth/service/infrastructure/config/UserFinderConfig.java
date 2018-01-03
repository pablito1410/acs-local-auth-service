package com.pablo.acs.local.auth.service.infrastructure.config;

import com.pablo.acs.local.auth.service.domain.ports.incoming.SystemProfile;
import com.pablo.acs.local.auth.service.domain.ports.outgoing.RestClient;
import com.pablo.acs.local.auth.service.domain.user.UserRepository;
import com.pablo.acs.local.auth.service.domain.user.query.UserFinder;
import com.pablo.acs.local.auth.service.infrastructure.repository.UserIdentifyMethodFinderJdbcRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserFinderConfig {

    @Bean
    public UserFinder userFinder(final UserRepository userRepository, final RestClient restClient,
                                 final SystemProfile systemProfile,
                                 final UserIdentifyMethodFinderJdbcRepository identifyMethodRepository) {
        return new UserFinder(userRepository, restClient, systemProfile, identifyMethodRepository);
    }
}
