package com.pablo.acs.local.auth.service.domain.user.exception;

public class IdentificationMethodNotExistException extends RuntimeException {

    public IdentificationMethodNotExistException(final String message) {
        super(message);
    }
}
