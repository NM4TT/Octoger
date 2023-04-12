package com.nmatute.octoger.typemanagement.domain.dao;

import java.util.List;
import java.util.Optional;

import com.nmatute.octoger.typemanagement.domain.dto.TypeDTO;

public interface ITypeRepository {
    
    List<TypeDTO> getAll();

    Optional<TypeDTO> getByIdentifier(String identifier);

    Optional<TypeDTO> getById(int id);
    
    TypeDTO save(TypeDTO user);
    
    void delete(int id);

}
