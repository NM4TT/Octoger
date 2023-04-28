package com.nmatute.octoger.accountingmanagement.persistence.repository;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.accountingmanagement.domain.dao.ITypeRepository;
import com.nmatute.octoger.accountingmanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.accountingmanagement.persistence.crud.ITypeCrudRepository;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.TypeMapper;

import lombok.RequiredArgsConstructor;

/**
 * Clase para Repositorio de Tipos.
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
