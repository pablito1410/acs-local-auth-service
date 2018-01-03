package com.pablo.acs.local.auth.service.domain.proxy.ports.outgoing;

import java.util.Collection;

public class SystemUsers {

    private long totalCount;
    private Collection<SystemUser> results;

    private SystemUsers() { }

    public SystemUsers(final Collection<SystemUser> results) {
        this.results = results;
        this.totalCount = results.size();
    }

    public long getTotalCount() {
        return totalCount;
    }

    public Collection<SystemUser> getResults() {
        return results;
    }
}
