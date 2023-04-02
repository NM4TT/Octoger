package com.nmatute.octoger.usermanagement.domain.service;

import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@Data
@RequiredArgsConstructor
public class EntityService {
    
    private final UserService userService;

    private final CredentialService credentialService;

}
