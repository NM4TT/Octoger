package com.nmatute.octoger.accountingmanagement.domain.service;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.accountingmanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.accountingmanagement.persistence.repository.TypeRepository;

import lombok.RequiredArgsConstructor;

/**
 * Clase para Servicio de Tipos.
 * 
 * @author NM4TT
 */
@Service
@RequiredArgsConstructor
public class TypeService {
    
    private final TypeRepository repo;
    
    public TypeDTO getByIdentifier(String identifier) {
        return repo.getByIdentifier(identifier);
    }

}
