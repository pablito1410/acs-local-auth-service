package com.pablo.acs.local.auth.service.domain.user.exception;

public class UserNotExistException extends RuntimeException {

    public UserNotExistException(final String message) {
        super(message);
    }
}
