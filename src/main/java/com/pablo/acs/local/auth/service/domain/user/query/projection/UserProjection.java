package com.pablo.acs.local.auth.service.domain.user.query.projection;

public class UserProjection {

    private String id;
    private String externalId;
    private String name;
    private Integer role;
    private String avatarUrl;

    private UserProjection() { }

    public UserProjection(final String id, final String externalId,
                          final String name, final String avatarUrl, final Integer role) {
        this.id = id;
        this.externalId = externalId;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getExternalId() {
        return externalId;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(final int role) {
        this.role = role;
    }
}
