package com.pablo.acs.local.auth.service.domain.user;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "EXTERNAL_ID", length = 1024, nullable = false, unique = true)
    private String externalId;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "PHOTO", length = 8096)
    private byte[] photo;

    @ManyToMany
    @JoinTable(name = "USERS_IDENTIFICATION_METHODS",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "IDENTIFICATION_METHOD_ID")}
    )
    private Collection<IdentifyMethod> identifyMethods;

    private User() { }

    User(final String externalId, final String name, final byte[] photo) {
        this.externalId = externalId;
        this.name = name;
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public String getExternalId() {
        return externalId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", externalId='" + externalId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }
}
