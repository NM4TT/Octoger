package com.nmatute.octoger.usermanagement.domain.dao;

import java.util.List;
import java.util.Optional;

import com.nmatute.octoger.usermanagement.domain.dto.TypeDTO;

public interface ITypeRepository {
 
    List<TypeDTO> getAll();
    Optional<TypeDTO> getById(int id);
    
    List<TypeDTO> getByIdentifier(String identifier);
    
    TypeDTO save(TypeDTO type);

    void delete(int id);
}
