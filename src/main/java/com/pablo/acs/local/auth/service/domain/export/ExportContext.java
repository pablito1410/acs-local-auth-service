package com.pablo.acs.local.auth.service.domain.export;

import java.time.LocalDateTime;

class ExportContext {

    private static LocalDateTime lastExportTime = LocalDateTime.of(1990, 01, 01, 0, 0, 0);

    static LocalDateTime getLastExportTime() {
        return lastExportTime;
    }

    static void setLastExportTime(final LocalDateTime dateTime) {
        lastExportTime = dateTime;
    }
}
