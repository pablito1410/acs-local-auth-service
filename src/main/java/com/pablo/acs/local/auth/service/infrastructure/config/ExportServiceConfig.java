package com.pablo.acs.local.auth.service.infrastructure.config;

import com.pablo.acs.local.auth.service.domain.export.ExportService;
import com.pablo.acs.local.auth.service.domain.ports.incoming.SystemProfile;
import com.pablo.acs.local.auth.service.domain.ports.outgoing.RestClient;
import com.pablo.acs.local.auth.service.domain.user.query.UserIdentifyMethodFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExportServiceConfig {

    @Bean
    public ExportService exportService(final RestClient restClient, final UserIdentifyMethodFinder finder,
                                       final SystemProfile systemProfile) {
        return new ExportService(restClient, finder, systemProfile);
    }
}
