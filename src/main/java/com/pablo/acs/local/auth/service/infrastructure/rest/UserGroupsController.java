package com.pablo.acs.local.auth.service.infrastructure.rest;

import com.pablo.acs.local.auth.service.domain.proxy.ExternalSystemProxy;
import com.pablo.acs.local.auth.service.domain.proxy.ports.outgoing.UserGroup;
import com.pablo.acs.local.auth.service.domain.proxy.ports.outgoing.UserGroupMember;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usergroups")
public class UserGroupsController {

    private final ExternalSystemProxy externalSystemProxy;

    public UserGroupsController(final ExternalSystemProxy externalSystemProxy) {
        this.externalSystemProxy = externalSystemProxy;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<UserGroup[]> get() {
        final UserGroup[] userGroups = externalSystemProxy.getUserGroups();
        return new ResponseEntity<>(userGroups, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/{id}/members")
    public ResponseEntity<UserGroupMember[]> getMembers(@PathVariable("id") final String id) {
        final UserGroupMember[] userGroups = externalSystemProxy.getUserGroupsMembers(id);
        return new ResponseEntity<>(userGroups, HttpStatus.OK);
    }
}
