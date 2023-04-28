package com.nmatute.octoger.usermanagement.domain.service;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.usermanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.usermanagement.persistence.repository.TypeRepository;

import lombok.RequiredArgsConstructor;

/**
 * Servicio de Tipos.
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
