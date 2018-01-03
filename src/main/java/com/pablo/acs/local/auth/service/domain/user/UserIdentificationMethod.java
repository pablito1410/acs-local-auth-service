package com.pablo.acs.local.auth.service.domain.user;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "USERS_IDENTIFICATION_METHODS")
@AssociationOverrides({
        @AssociationOverride(name = "id.user",
                joinColumns = @JoinColumn(name = "USER_ID")),
        @AssociationOverride(name = "id.identifyMethod",
                joinColumns = @JoinColumn(name = "IDENTIFICATION_METHOD_ID")) })
public class UserIdentificationMethod {

    @EmbeddedId
    private UserIdentificationMethodId id;

    @Column(name = "IDENTIFIER", length = 8096, nullable = false)
    private byte[] identifier;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-mm-ddThh:mm:ss")
    @Column(name = "LAST_UPDATE", nullable = false)
    private Date lastUpdate;

    private UserIdentificationMethod() { }

    UserIdentificationMethod(final User user,
                             final IdentifyMethod identifyMethod,
                             final byte[] identifier, final LocalDateTime lastUpdate) {
        this.lastUpdate = Date.from(lastUpdate.atZone(ZoneId.systemDefault()).toInstant());
        this.id = new UserIdentificationMethodId(user, identifyMethod);
        this.identifier = identifier;
    }

    @Embeddable
    public static class UserIdentificationMethodId implements Serializable {

        @ManyToOne(optional = false, targetEntity = User.class, cascade = CascadeType.ALL)
        private User user;

        @ManyToOne(optional = false, targetEntity = IdentifyMethod.class)
        private IdentifyMethod identifyMethod;

        private UserIdentificationMethodId() { }

        UserIdentificationMethodId(final User user, final IdentifyMethod identifyMethod) {
            this.user = user;
            this.identifyMethod = identifyMethod;
        }
    }

}
