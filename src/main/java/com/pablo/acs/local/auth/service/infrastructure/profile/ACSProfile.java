package com.pablo.acs.local.auth.service.infrastructure.profile;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "acs")
public class ACSProfile {

    private int maxUsers;

    public int getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(final int maxUsers) {
        this.maxUsers = maxUsers;
    }
}
