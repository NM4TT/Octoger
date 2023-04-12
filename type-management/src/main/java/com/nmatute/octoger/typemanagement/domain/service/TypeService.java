package com.nmatute.octoger.typemanagement.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.typemanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.typemanagement.persistence.repository.TypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TypeService {
    
    private final TypeRepository repo;

    public List<TypeDTO> getAll() {
        return repo.getAll();
    }

    
    public Optional<TypeDTO> getByIdentifier(String identifier) {
        return repo.getByIdentifier(identifier);
    }

    public Optional<TypeDTO> getById(int id){
        return repo.getById(id);
    }

    
    public TypeDTO save(TypeDTO user) {
        return repo.save(user);
    }

    
    public void delete(int id) {
        repo.delete(id);
    }
}
