package com.pablo.acs.local.auth.service.infrastructure.rest;

import com.pablo.acs.local.auth.service.domain.proxy.ExternalSystemProxy;
import com.pablo.acs.local.auth.service.domain.proxy.ports.outgoing.SystemUsers;
import com.pablo.acs.local.auth.service.domain.user.command.CreateUserCommand;
import com.pablo.acs.local.auth.service.domain.user.query.UserFinder;
import com.pablo.acs.local.auth.service.domain.user.query.UserIdentifyMethodFinder;
import com.pablo.acs.local.auth.service.domain.user.query.projection.UserProjection;
import com.pablo.acs.local.auth.service.infrastructure.command.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final CommandGateway commandGateway;
    private final UserIdentifyMethodFinder userIdentifyMethodFinder;
    private final ExternalSystemProxy externalSystemProxy;
    private final UserFinder userFinder;

    UsersController(final CommandGateway commandGateway, final UserIdentifyMethodFinder userIdentifyMethodFinder,
                    final ExternalSystemProxy externalSystemProxy, final UserFinder userFinder) {
        this.commandGateway = commandGateway;
        this.userIdentifyMethodFinder = userIdentifyMethodFinder;
        this.externalSystemProxy = externalSystemProxy;
        this.userFinder = userFinder;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody final CreateUserCommand command) {
        commandGateway.dispatch(command);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<SystemUsers> get() {
        final SystemUsers users = userFinder.getSystemUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/{id}")
    public ResponseEntity<UserProjection> getById(@PathVariable("id") final Long id) {
        final UserProjection user = userFinder.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/pin/{pin}")
    public ResponseEntity<UserProjection> getByPin(@PathVariable("pin") final String pin) {
        final UserProjection user = userFinder.getByPin(pin);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
