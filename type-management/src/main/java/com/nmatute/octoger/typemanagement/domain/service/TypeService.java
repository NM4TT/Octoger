package com.nmatute.octoger.typemanagement.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.typemanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.typemanagement.persistence.repository.TypeRepository;

import lombok.RequiredArgsConstructor;

/**
 * Servicio de tipos.
 * 
 * @author NM4TT
 */
@Service
@RequiredArgsConstructor
public class TypeService {
    
    private final TypeRepository repo;

    public List<TypeDTO> getAll() {
        return repo.getAll();
    }

    
    public TypeDTO getByIdentifier(String identifier) {
        return repo.getByIdentifier(identifier);
    }

    public TypeDTO getById(long id){
        return repo.getById(id);
    }

    public List<TypeDTO> getByPrefix(String prefix){
        return repo.getByPrefix(prefix);
    }

    public TypeDTO save(TypeDTO user) {
        return repo.save(user);
    }

    
    public void delete(long id) {
        repo.delete(id);
    }
}
