package com.pablo.acs.local.auth.service.domain.proxy.ports.outgoing;

public class Attribute {

    private String _id;
    private String name;
    private String value;

    private Attribute() { }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
