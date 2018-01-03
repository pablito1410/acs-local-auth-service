package com.pablo.acs.local.auth.service.domain.user.query;

import com.pablo.acs.local.auth.service.domain.ports.incoming.SystemProfile;
import com.pablo.acs.local.auth.service.domain.ports.outgoing.RestClient;
import com.pablo.acs.local.auth.service.domain.proxy.ports.outgoing.SystemUser;
import com.pablo.acs.local.auth.service.domain.proxy.ports.outgoing.SystemUsers;
import com.pablo.acs.local.auth.service.domain.user.IdentifyMethodType;
import com.pablo.acs.local.auth.service.domain.user.User;
import com.pablo.acs.local.auth.service.domain.user.UserRepository;
import com.pablo.acs.local.auth.service.domain.user.exception.UserNotExistException;
import com.pablo.acs.local.auth.service.domain.user.query.projection.UserIdentifyMethodProjection;
import com.pablo.acs.local.auth.service.domain.user.query.projection.UserProjection;
import com.pablo.acs.local.auth.service.infrastructure.repository.UserIdentifyMethodFinderJdbcRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class UserFinder {

    private final UserRepository userRepository;
    private final RestClient restClient;
    private final SystemProfile systemProfile;
    private final UserIdentifyMethodFinderJdbcRepository identifyMethodRepository;

    public UserFinder(final UserRepository userRepository, final RestClient restClient,
                      final SystemProfile systemProfile,
                      final UserIdentifyMethodFinderJdbcRepository identifyMethodRepository) {
        this.userRepository = userRepository;
        this.restClient = restClient;
        this.systemProfile = systemProfile;
        this.identifyMethodRepository = identifyMethodRepository;
    }

    public UserProjection getById(final Long id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotExistException("id=" + id));
        return new UserProjection(String.valueOf(user.getId()), user.getExternalId(),
                user.getName(), null, null);
    }

    public UserProjection getByPin(final String pin) {
        final Collection<UserIdentifyMethodProjection> users
                = identifyMethodRepository.findByIdentifyMethod(IdentifyMethodType.PIN);

        for (final UserIdentifyMethodProjection user : users) {
            if (Arrays.equals(user.getIdentifier(), pin.getBytes())) {
                final UserProjection selected = getById(user.getUserId());
                selected.setRole(1);
                return selected;
            }
        }
        throw new UserNotExistException("pin=" + pin);
    }

    public Collection<UserProjection> getAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserProjection(String.valueOf(user.getId()), user.getExternalId(),
                        user.getName(), null, null))
                .collect(Collectors.toList());
    }

    public SystemUsers getSystemUsers() {
        return new SystemUsers(userRepository.findAll()
                .stream()
                .map(user -> new SystemUser(user.getId(), user.getExternalId(), user.getName()))
                .collect(Collectors.toList()));
    }
}
