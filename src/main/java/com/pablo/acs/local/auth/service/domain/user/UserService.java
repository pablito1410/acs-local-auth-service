package com.pablo.acs.local.auth.service.domain.user;

import com.pablo.acs.local.auth.service.domain.user.command.AddUserIdentificationMethod;
import com.pablo.acs.local.auth.service.domain.user.command.CreateUserCommand;
import com.pablo.acs.local.auth.service.domain.user.exception.IdentificationMethodNotExistException;
import com.pablo.acs.local.auth.service.domain.user.exception.UserNotExistException;

import java.time.LocalDateTime;

public class UserService {

    private final UserRepository userRepository;
    private final UserIdentificationMethodRepository userIdentifyMethodRepository;
    private final IdentifyMethodRepository identifyMethodRepository;

    public UserService(final UserRepository userRepository,
                       final UserIdentificationMethodRepository userIdentifyMethodRepository,
                       final IdentifyMethodRepository identifyMethodRepository) {
        this.userRepository = userRepository;
        this.userIdentifyMethodRepository = userIdentifyMethodRepository;
        this.identifyMethodRepository = identifyMethodRepository;
    }

    public void handle(final CreateUserCommand command) {
        final User user = new User(command.getExternalId(), command.getName(), command.getPhoto());
        userRepository.save(user);
    }

    public void handle(final AddUserIdentificationMethod command) {

        final Integer identificationMethodId = command.getIdentificationMethodId();
        final IdentifyMethod identifyMethod =
                identifyMethodRepository.findById(identificationMethodId)
                        .orElseThrow(() -> new IdentificationMethodNotExistException(
                                "identificationMethodId=" + identificationMethodId));

        final Long userId = command.getUserId();
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistException("userId=" + userId));

        final byte[] identifier = command.getIdentifier();
        final UserIdentificationMethod userIdentificationMethod
                = new UserIdentificationMethod(user, identifyMethod, identifier, LocalDateTime.now());
        userIdentifyMethodRepository.save(userIdentificationMethod);
    }
}
