package com.nmatute.octoger.typemanagement.domain.dao;

import java.util.List;

import com.nmatute.octoger.typemanagement.domain.dto.TypeDTO;

public interface ITypeRepository {
    
    List<TypeDTO> getAll();

    TypeDTO getByIdentifier(String identifier);

    List<TypeDTO> getByPrefix(String prefix);

    TypeDTO getById(int id);
    
    TypeDTO save(TypeDTO user);
    
    void delete(int id);

}
