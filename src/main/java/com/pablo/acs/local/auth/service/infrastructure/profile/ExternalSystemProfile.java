package com.pablo.acs.local.auth.service.infrastructure.profile;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "external.system")
public class ExternalSystemProfile {


    private String systemusersUrl;
    private String apiKey;
    private String usergroupsUrl;
    private String usergroupsMembersUrl;

    public String getSystemusersUrl() {
        return systemusersUrl;
    }

    public void setSystemusersUrl(final String systemusersUrl) {
        this.systemusersUrl = systemusersUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(final String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUsergroupsUrl() {
        return usergroupsUrl;
    }

    public void setUsergroupsUrl(final String usergroupsUrl) {
        this.usergroupsUrl = usergroupsUrl;
    }

    public String getUsergroupsMembersUrl() {
        return usergroupsMembersUrl;
    }

    public void setUsergroupsMembersUrl(final String usergroupsMembersUrl) {
        this.usergroupsMembersUrl = usergroupsMembersUrl;
    }
}
