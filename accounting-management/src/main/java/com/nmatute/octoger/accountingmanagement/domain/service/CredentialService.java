package com.nmatute.octoger.accountingmanagement.domain.service;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.accountingmanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.accountingmanagement.persistence.repository.CredentialRepository;

import lombok.RequiredArgsConstructor;

/**
 * Clase para Servicio de Credenciales.
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
