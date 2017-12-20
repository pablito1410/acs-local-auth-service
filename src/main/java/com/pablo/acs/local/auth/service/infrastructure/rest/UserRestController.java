package com.pablo.acs.local.auth.service.infrastructure.rest;

import com.pablo.acs.local.auth.service.domain.user.IdentifyMethodType;
import com.pablo.acs.local.auth.service.domain.user.command.CreateUserCommand;
import com.pablo.acs.local.auth.service.domain.user.query.UserIdentifyMethodFinder;
import com.pablo.acs.local.auth.service.domain.user.query.UserIdentifyMethodProjection;
import com.pablo.acs.local.auth.service.infrastructure.command.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final CommandGateway commandGateway;
    private final UserIdentifyMethodFinder userIdentifyMethodFinder;

    UserRestController(final CommandGateway commandGateway, final UserIdentifyMethodFinder userIdentifyMethodFinder) {
        this.commandGateway = commandGateway;
        this.userIdentifyMethodFinder = userIdentifyMethodFinder;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody final CreateUserCommand command) {
        commandGateway.dispatch(command);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Collection<UserIdentifyMethodProjection>> getUserIdentificationMethods(
            @RequestParam(name = "identificationMethod") final IdentifyMethodType identificationMethod) {
        final Collection<UserIdentifyMethodProjection> identificationMethods
                = userIdentifyMethodFinder.findAll(identificationMethod);
        return new ResponseEntity<>(identificationMethods, HttpStatus.OK);
    }
}
