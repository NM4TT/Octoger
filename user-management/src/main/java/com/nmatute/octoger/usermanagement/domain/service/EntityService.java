package com.nmatute.octoger.usermanagement.domain.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class EntityService {
    
    private UserService userService;

    private CredentialService credentialService;

}
