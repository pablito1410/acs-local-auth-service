package com.pablo.acs.local.auth.service.infrastructure.update;

import com.pablo.acs.local.auth.service.domain.export.command.ExportIdentifiersCommand;
import com.pablo.acs.local.auth.service.domain.ports.incoming.SystemProfile;
import com.pablo.acs.local.auth.service.domain.update.UpdateService;
import com.pablo.acs.local.auth.service.domain.update.command.UpdateDatabaseCommand;
import com.pablo.acs.local.auth.service.infrastructure.export.ExportScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UpdateScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateScheduler.class);
    private final UpdateService updateService;
    private final SystemProfile systemProfile;

    @Autowired
    public UpdateScheduler(final UpdateService updateService,
                           final SystemProfile systemProfile) {
        this.updateService = updateService;
        this.systemProfile = systemProfile;
    }

    @Scheduled(fixedDelay = 15000)
    void updateDatabase() {
        updateService.handle(new UpdateDatabaseCommand());
    }
}
