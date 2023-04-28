package com.nmatute.octoger.usermanagement.persistence.repository;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.usermanagement.domain.dao.ITypeRepository;
import com.nmatute.octoger.usermanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.usermanagement.persistence.crud.ITypeCrudRepository;
import com.nmatute.octoger.usermanagement.persistence.mapper.TypeMapper;

import lombok.RequiredArgsConstructor;

/**
 * Repositorio de Tipos.
 * 
 * @author NM4TT
 */
@Repository
@RequiredArgsConstructor
public class TypeRepository implements ITypeRepository {

    private final ITypeCrudRepository crud;
    private final TypeMapper mapper;

    @Override
    public TypeDTO getByIdentifier(String identifier) {
        return mapper.toTypeDTO(crud.findByIdentifier(identifier));
    }
    
}
