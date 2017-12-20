package com.pablo.acs.local.auth.service.infrastructure.rest;

import com.pablo.acs.local.auth.service.domain.user.IdentifyMethodType;
import com.pablo.acs.local.auth.service.domain.user.query.IdentifiersWrapper;
import com.pablo.acs.local.auth.service.domain.user.query.UserIdentifyMethodFinder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/update")
public class UpdateController {

    private final UserIdentifyMethodFinder finder;

    public UpdateController(final UserIdentifyMethodFinder finder) {
        this.finder = finder;
    }

    @GetMapping
    public ResponseEntity<IdentifiersWrapper> update(
            @RequestParam(value = "identification-method", required = false) final IdentifyMethodType identifyMethod,
            @RequestParam(value = "lastUpdate", required = false) final String lastUpdate) {
        final IdentifiersWrapper identifiers = finder.findAll(
                identifyMethod, LocalDateTime.parse(lastUpdate, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return new ResponseEntity<>(identifiers, HttpStatus.OK);
    }
}
