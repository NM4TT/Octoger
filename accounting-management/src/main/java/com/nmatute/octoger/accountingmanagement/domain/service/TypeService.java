package com.nmatute.octoger.accountingmanagement.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.accountingmanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.accountingmanagement.persistence.repository.TypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TypeService {
    
    private final TypeRepository repo;
    
    public Optional<TypeDTO> getByIdentifier(String identifier) {
        return repo.getByIdentifier(identifier);
    }

}
