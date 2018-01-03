package com.pablo.acs.local.auth.service.domain.exception;

public class RestClientException extends RuntimeException {

    public RestClientException(final Throwable e) {
        super(e);
    }
}
