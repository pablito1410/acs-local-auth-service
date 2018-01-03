package com.pablo.acs.local.auth.service.domain.ports.incoming;

public interface Response<T> {
    int getStatusCode();

    T getBody();
}
