package com.nmatute.octoger.typemanagement.domain.service;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.typemanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.typemanagement.persistence.repository.CredentialRepository;

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

    public CredentialDTO findByUsername(String username){
        return repo.findByUsername(username);
    }

}
