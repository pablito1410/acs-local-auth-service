package com.pablo.acs.local.auth.service.infrastructure.export;

import com.pablo.acs.local.auth.service.domain.export.ExportService;
import com.pablo.acs.local.auth.service.domain.export.command.ExportIdentifiersCommand;
import com.pablo.acs.local.auth.service.domain.ports.incoming.SystemProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExportScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportScheduler.class);
    private final ExportService exportService;
    private final SystemProfile systemProfile;

    @Autowired
    public ExportScheduler(final ExportService exportService,
                           final SystemProfile systemProfile) {
        this.exportService = exportService;
        this.systemProfile = systemProfile;
    }

    @Scheduled(fixedDelay = 15000)
    void exportDatabaseChanges() {
        exportService.handle(new ExportIdentifiersCommand());
    }
}
