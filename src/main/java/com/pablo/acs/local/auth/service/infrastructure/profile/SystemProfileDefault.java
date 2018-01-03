package com.pablo.acs.local.auth.service.infrastructure.profile;

import com.pablo.acs.local.auth.service.domain.ports.incoming.SystemProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SystemProfileDefault implements SystemProfile {

    private final ExportProfile exportProfile;
    private final ExternalSystemProfile externalSystemProfile;
    private final ACSProfile acsProfile;

    @Autowired
    public SystemProfileDefault(final ExportProfile exportProfile,
                                final ExternalSystemProfile externalSystemProfile, final ACSProfile acsProfile) {
        this.exportProfile = exportProfile;
        this.externalSystemProfile = externalSystemProfile;
        this.acsProfile = acsProfile;
    }

    @Override
    public Optional<String> exportUrl() {
        return Optional.ofNullable(exportProfile.getUrl());
    }

    @Override
    public String externalSystemGetUsersUrl() {
        return externalSystemProfile.getSystemusersUrl();
    }

    @Override
    public String externalSystemApiKey() {
        return externalSystemProfile.getApiKey();
    }

    @Override
    public String externalSystemGetUserGroupsUrl() {
        return externalSystemProfile.getUsergroupsUrl();
    }

    @Override
    public String externalSystemGetUserGroupMembersUrl(final String id) {
        final String url = externalSystemProfile.getUsergroupsMembersUrl();
        if (url != null) {
            return String.format(url, id);
        } else {
            return null;
        }
    }

    @Override
    public int maxNumberOfUsers() {
        return acsProfile.getMaxUsers();
    }
}
