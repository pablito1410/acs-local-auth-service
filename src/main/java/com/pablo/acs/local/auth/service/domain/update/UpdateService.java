package com.pablo.acs.local.auth.service.domain.update;

import com.pablo.acs.local.auth.service.domain.ports.incoming.SystemProfile;
import com.pablo.acs.local.auth.service.domain.proxy.ExternalSystemProxy;
import com.pablo.acs.local.auth.service.domain.proxy.ports.outgoing.SystemUser;
import com.pablo.acs.local.auth.service.domain.update.command.UpdateDatabaseCommand;
import com.pablo.acs.local.auth.service.domain.user.UserService;
import com.pablo.acs.local.auth.service.domain.user.command.CreateUserCommand;
import com.pablo.acs.local.auth.service.domain.user.command.DeleteUserCommand;
import com.pablo.acs.local.auth.service.domain.user.command.UpdateUserCommand;
import com.pablo.acs.local.auth.service.domain.user.query.UserFinder;
import com.pablo.acs.local.auth.service.domain.user.query.projection.UserProjection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class UpdateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateService.class);
    private final UserFinder userFinder;
    private final ExternalSystemProxy externalSystemProxy;
    private final SystemProfile systemProfile;
    private final UserService userService;

    public UpdateService(final UserFinder userFinder, final ExternalSystemProxy externalSystemProxy, final SystemProfile systemProfile, final UserService userService) {
        this.userFinder = userFinder;
        this.externalSystemProxy = externalSystemProxy;
        this.systemProfile = systemProfile;
        this.userService = userService;
    }

    public void handle(final UpdateDatabaseCommand command) {
        LOGGER.info("Update database process started.");
        final Map<String, SystemUser> externalUsers = externalSystemProxy.getSystemUsers()
                .getResults()
                .stream()
                .collect(Collectors.toMap(SystemUser::get_id, user -> user));

        final Map<String, UserProjection> users = userFinder.getAll()
                .stream()
                .collect(Collectors.toMap(UserProjection::getExternalId, user -> user));

        updateDatabase(externalUsers, users);
        LOGGER.info("Update database process finished.");
    }

    private void updateDatabase(final Map<String, SystemUser> externalUsers, final Map<String, UserProjection> users) {
        final Collection<SystemUser> toInsert = new ArrayList<>();
        final Collection<SystemUser> toUpdate = new ArrayList<>();
        final Collection<UserProjection> toDelete = new ArrayList<>();

        LOGGER.info("Comparing data...");

        for (final Map.Entry<String, SystemUser> systemUser : externalUsers.entrySet()) {
            if (users.containsKey(systemUser.getKey())) {
                toUpdate.add(systemUser.getValue());
            } else if (users.size() < systemProfile.maxNumberOfUsers()) {
                toInsert.add(systemUser.getValue());
            }
        }

        for (final Map.Entry<String, UserProjection> user : users.entrySet()) {
            if (!externalUsers.containsKey(user.getKey())) {
                toDelete.add(user.getValue());
            }
        }
        LOGGER.info("To insert: {} users", toInsert.size());
        LOGGER.info("To update: {} users", toUpdate.size());
        LOGGER.info("To delete: {} users", toDelete.size());
        toInsert.forEach(systemUser -> userService
                .handle(new CreateUserCommand(systemUser.getNameText(), null, systemUser.get_id())));
        toUpdate.forEach(systemUser -> userService
                .handle(new UpdateUserCommand(systemUser.get_id(), systemUser.getNameText())));
        toDelete.forEach(user -> userService.handle(new DeleteUserCommand(Long.valueOf(user.getId()))));
    }

}
