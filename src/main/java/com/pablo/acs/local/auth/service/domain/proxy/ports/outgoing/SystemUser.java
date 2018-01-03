package com.pablo.acs.local.auth.service.domain.proxy.ports.outgoing;

import java.util.Collection;

public class SystemUser {

    private  Long id;
    private String _id;
    private String email;
    private String username;
    private String firstname;
    private String lastname;
    private Collection<Attribute> attributes;

    private SystemUser() { }

    SystemUser(final Long id, final String _id, final String email, final String username,
               final String firstname, final String lastname, final Collection<Attribute> attributes) {
        this.id = id;
        this._id = _id;
        this.email = email;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.attributes = attributes;
    }

    public SystemUser(final Long id, final String externalId, final String name) {
        this.id = id;
        this._id = externalId;
        this.username = name;
    }

    public String get_id() {
        return _id;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Collection<Attribute> getAttributes() {
        return attributes;
    }

    public String getNameText() {
        if (firstname == null || lastname == null) {
            return username;
        } else {
            return firstname + " " + lastname;
        }
    }
}
