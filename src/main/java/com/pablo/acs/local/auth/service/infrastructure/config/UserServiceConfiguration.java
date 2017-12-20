package com.pablo.acs.local.auth.service.infrastructure.config;

import com.pablo.acs.local.auth.service.domain.user.IdentifyMethodRepository;
import com.pablo.acs.local.auth.service.domain.user.UserIdentificationMethodRepository;
import com.pablo.acs.local.auth.service.domain.user.UserRepository;
import com.pablo.acs.local.auth.service.domain.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfiguration {

    @Bean
    public UserService userService(final UserRepository userRepository,
                                   final IdentifyMethodRepository identifyMethodRepository,
                                   final UserIdentificationMethodRepository userIdentificationMethodRepository) {
        return new UserService(userRepository, userIdentificationMethodRepository, identifyMethodRepository);
    }
}
