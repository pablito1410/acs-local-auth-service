package com.pablo.acs.local.auth.service.domain.proxy;

import com.pablo.acs.local.auth.service.domain.ports.incoming.SystemProfile;
import com.pablo.acs.local.auth.service.domain.ports.outgoing.RestClient;
import com.pablo.acs.local.auth.service.domain.proxy.ports.outgoing.SystemUsers;
import com.pablo.acs.local.auth.service.domain.proxy.ports.outgoing.UserGroup;
import com.pablo.acs.local.auth.service.domain.proxy.ports.outgoing.UserGroupMember;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.Collections;

public class ExternalSystemProxy {

    private final RestClient restClient;
    private final SystemProfile systemProfile;
    private final MultiValueMap<String, String> httpHeaders = new LinkedMultiValueMap<>();

    public ExternalSystemProxy(final RestClient restClient, final SystemProfile systemProfile) {
        this.restClient = restClient;
        this.systemProfile = systemProfile;
        httpHeaders.put("x-api-key", Arrays.asList(this.systemProfile.externalSystemApiKey()));
        httpHeaders.put(HttpHeaders.CONTENT_TYPE, Arrays.asList("application/json"));
    }

    public SystemUsers getSystemUsers() {
        return restClient.get(
                systemProfile.externalSystemGetUsersUrl(),
                SystemUsers.class,
                Collections.emptyMap(),
                httpHeaders).getBody();
    }

    public UserGroup[] getUserGroups() {
        return restClient.get(
                systemProfile.externalSystemGetUserGroupsUrl(),
                UserGroup[].class,
                Collections.emptyMap(),
                httpHeaders).getBody();
    }

    public UserGroupMember[] getUserGroupsMembers(final String id) {
        return restClient.get(
                systemProfile.externalSystemGetUserGroupMembersUrl(id),
                UserGroupMember[].class,
                Collections.emptyMap(),
                httpHeaders).getBody();
    }
}
