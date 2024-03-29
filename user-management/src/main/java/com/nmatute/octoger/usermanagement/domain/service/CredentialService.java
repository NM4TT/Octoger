package com.nmatute.octoger.usermanagement.domain.service;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.usermanagement.persistence.repository.CredentialRepository;

import lombok.RequiredArgsConstructor;

/**
 * Servicio de Credenciales.
 * 
 * @author NM4TT
 */
@Service
@RequiredArgsConstructor
public class CredentialService {

    private final CredentialRepository repo;
    
    public CredentialDTO save(CredentialDTO credential) {
        return repo.save(credential);
    }
    
    public long findIdByUsername(String username){
        return repo.findIdByUsername(username);
    }

    public CredentialDTO findByUsername(String username){
        CredentialDTO credential = repo.findByUsername(username);
        return credential;
    }

}
