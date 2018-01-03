package com.pablo.acs.local.auth.service.infrastructure.config;

import com.pablo.acs.local.auth.service.domain.ports.incoming.SystemProfile;
import com.pablo.acs.local.auth.service.domain.proxy.ExternalSystemProxy;
import com.pablo.acs.local.auth.service.domain.update.UpdateService;
import com.pablo.acs.local.auth.service.domain.user.UserService;
import com.pablo.acs.local.auth.service.domain.user.query.UserFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateServiceConfig {

    @Bean
    public UpdateService updateService(final UserFinder userFinder, final ExternalSystemProxy externalSystemProxy,
                                       final SystemProfile systemProfile, final UserService userService) {

        return new UpdateService(userFinder, externalSystemProxy, systemProfile, userService);
    }
}
