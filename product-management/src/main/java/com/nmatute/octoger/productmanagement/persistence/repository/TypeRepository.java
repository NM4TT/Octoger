package com.nmatute.octoger.productmanagement.persistence.repository;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.productmanagement.domain.dao.ITypeRepository;
import com.nmatute.octoger.productmanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.productmanagement.persistence.crud.ITypeCrudRepository;
import com.nmatute.octoger.productmanagement.persistence.mapper.TypeMapper;

import lombok.RequiredArgsConstructor;

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
