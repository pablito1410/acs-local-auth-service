package com.pablo.acs.local.auth.service.domain.user;

import com.pablo.acs.local.auth.service.domain.export.ExportService;
import com.pablo.acs.local.auth.service.domain.user.command.*;
import com.pablo.acs.local.auth.service.domain.user.exception.IdentificationMethodNotExistException;
import com.pablo.acs.local.auth.service.domain.user.exception.UserNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportService.class);

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
        LOGGER.info("Creating user {}", command);
        final User user = new User(command.getExternalId(), command.getName(), command.getPhoto());
        userRepository.save(user);
        LOGGER.info("User was created {}", user);
    }

    public void handle(final AddUserIdentificationMethod command) {
        LOGGER.info("Adding user identification methods {}", command);
        if (command.getIdentifiers() != null) {
            command.getIdentifiers().forEach(identifier -> addUserIdentificationMethod(identifier));
        }
    }

    private void addUserIdentificationMethod(final UserIdentifier command) {
        final Integer identificationMethodId = command.getIdentificationMethodId();
        final IdentifyMethod identifyMethod =
                identifyMethodRepository.findById(identificationMethodId)
                        .orElseThrow(() -> new IdentificationMethodNotExistException(
                                "identificationMethodId=" + identificationMethodId));

        final Long userId = command.getUserId();
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistException("userId=" + userId));

        LOGGER.info("Adding identification method {} for {}", identifyMethod, user);

        final byte[] identifier = convertToBytes(command);
        final UserIdentificationMethod userIdentificationMethod
                = new UserIdentificationMethod(user, identifyMethod, identifier, LocalDateTime.now());
        userIdentifyMethodRepository.save(userIdentificationMethod);
    }

    private byte[] convertToBytes(final UserIdentifier command) {
        final IdentifyMethodType methodType = IdentifyMethodType.getInstance(command.getIdentificationMethodId());
        if (methodType == null) {
            throw new IdentificationMethodNotExistException("id=" + command.getIdentificationMethodId());
        }

        switch (methodType) {
            case FINGERPRINT_SCANNER:
                return command.getIdentifier();
            case CARD_READER:
                return command.getIdentifier();
            case PIN:
                return command.getPin().getBytes();
                default:
                    throw new IdentificationMethodNotExistException("id=" + methodType.getId());
        }
    }

    public void handle(final UpdateUserCommand command) {
        LOGGER.info("Updating user, externalId={}", command.getExternalId());
        final User user = userRepository.findByExternalId(command.getExternalId())
                .orElseThrow(() -> new UserNotExistException("externalId=" + command.getExternalId()));
        user.setName(command.getName());
        userRepository.save(user);
        LOGGER.info("User updated {}", user);
    }

    public void handle(final DeleteUserCommand command) {
        LOGGER.info("Deleting user, userId={}", command.getId());
        if (command.getId() == 0) {
            LOGGER.info("Cannot delete the default user");
        } else {
            userRepository.delete(command.getId());
            LOGGER.info("User deleted, userId={}", command.getId());
        }
    }
}
