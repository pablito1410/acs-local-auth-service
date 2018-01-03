package com.pablo.acs.local.auth.service.domain.ports.incoming;

import java.util.Optional;

public interface SystemProfile {

    Optional<String> exportUrl();

    String externalSystemGetUsersUrl();

    String externalSystemApiKey();

    String externalSystemGetUserGroupsUrl();

    String externalSystemGetUserGroupMembersUrl(final String id);

    int maxNumberOfUsers();
}
