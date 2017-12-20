package com.pablo.acs.local.auth.service.infrastructure.config;

import com.pablo.acs.local.auth.service.domain.user.query.UserIdentifyMethodFinder;
import com.pablo.acs.local.auth.service.infrastructure.repository.UserIdentifyMethodFinderJdbcRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdentificationMethodFinderConfig {

    @Bean
    public UserIdentifyMethodFinder userIdentificationMethodFinder(
            final UserIdentifyMethodFinderJdbcRepository repository) {
        return new UserIdentifyMethodFinder(repository);
    }
}
