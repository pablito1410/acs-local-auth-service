package com.pablo.acs.local.auth.service.domain.user;

import javax.persistence.*;

@Entity
@Table(name = "IDENTIFICATION_METHODS")
public class IdentifyMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME", nullable = false, length = 50, unique = true)
    private String name;

    private IdentifyMethod() {

    }

    Integer getId() {
        return id;
    }

    String getName() {
        return name;
    }
}
