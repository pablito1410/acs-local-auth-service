package com.pablo.acs.local.auth.service.domain.export;

import com.pablo.acs.local.auth.service.domain.export.command.ExportIdentifiersCommand;
import com.pablo.acs.local.auth.service.domain.ports.incoming.SystemProfile;
import com.pablo.acs.local.auth.service.domain.ports.outgoing.RestClient;
import com.pablo.acs.local.auth.service.domain.user.query.UserIdentifyMethodFinder;
import com.pablo.acs.local.auth.service.domain.user.query.projection.IdentifiersWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class ExportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportService.class);

    private final RestClient restClient;
    private final UserIdentifyMethodFinder finder;
    private final SystemProfile systemProfile;

    public ExportService(final RestClient restClient, final UserIdentifyMethodFinder finder,
                         final SystemProfile systemProfile) {
        this.restClient = restClient;
        this.finder = finder;
        this.systemProfile = systemProfile;
    }

    public void handle(final ExportIdentifiersCommand command) {
        if (!systemProfile.exportUrl().isPresent()) {
            LOGGER.info("No export.service.url properties defined");
            return;
        }

        LOGGER.info("Export identifiers process start");
        exportDatabaseChanges();
        LOGGER.info("Export identifiers process finished");
    }

    private void exportDatabaseChanges() {
        final IdentifiersWrapper toExport = finder.findAll(ExportContext.getLastExportTime());

        if (toExport.getIdentifiers().isEmpty()) {
            LOGGER.info("No database changes");
            return;
        }

        LOGGER.info("Identifiers to export: {}", toExport);

        LOGGER.info("Sending POST to {}", systemProfile.exportUrl());
        restClient.post(systemProfile.exportUrl().get(), toExport);
        ExportContext.setLastExportTime(LocalDateTime.now());
    }
}
