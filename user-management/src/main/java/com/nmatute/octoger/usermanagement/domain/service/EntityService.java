package com.nmatute.octoger.usermanagement.domain.service;

import org.springframework.stereotype.Service;

import lombok.Data;

@Service
@Data
public class EntityService {
    
    private UserService userService;

    private CredentialService credentialService;

}
