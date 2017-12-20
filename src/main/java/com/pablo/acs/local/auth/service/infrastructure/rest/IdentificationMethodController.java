package com.pablo.acs.local.auth.service.infrastructure.rest;

import com.pablo.acs.local.auth.service.domain.user.command.AddUserIdentificationMethod;
import com.pablo.acs.local.auth.service.infrastructure.command.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/identification-methods")
public class IdentificationMethodController {

    private final CommandGateway commandGateway;

    IdentificationMethodController(final CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public ResponseEntity<Void> addUserIdentificationMethod(
            @RequestBody final AddUserIdentificationMethod command) {
        commandGateway.dispatch(command);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
