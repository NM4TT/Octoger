package com.nmatute.octoger.productmanagement.domain.service;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.productmanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.productmanagement.persistence.repository.CredentialRepository;

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
