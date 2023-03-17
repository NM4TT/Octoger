package com.nmatute.octoger.typemanagement.domain.dao;

import java.util.List;
import java.util.Optional;

import com.nmatute.octoger.typemanagement.domain.dto.TypeDTO;

public interface ITypeRepository {
 
    List<TypeDTO> getAll();
    Optional<TypeDTO> getById(int id);
    
    List<TypeDTO> getByIdentifier(String identifier);
    
    TypeDTO save(TypeDTO type);

    void delete(int id);
}
