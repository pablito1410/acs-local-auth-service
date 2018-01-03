package com.pablo.acs.local.auth.service.infrastructure.config;

import com.pablo.acs.local.auth.service.domain.ports.incoming.SystemProfile;
import com.pablo.acs.local.auth.service.domain.ports.outgoing.RestClient;
import com.pablo.acs.local.auth.service.domain.proxy.ExternalSystemProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternalSystemConfig {

    @Bean
    public ExternalSystemProxy externalSystemProxy(final RestClient restClient,
                                                   final SystemProfile systemProfile) {
        return new ExternalSystemProxy(restClient, systemProfile);
    }
}
