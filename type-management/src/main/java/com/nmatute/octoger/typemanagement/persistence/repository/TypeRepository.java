package com.nmatute.octoger.typemanagement.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.typemanagement.domain.dao.ITypeRepository;
import com.nmatute.octoger.typemanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.typemanagement.persistence.crud.ITypeCrudRepository;
import com.nmatute.octoger.typemanagement.persistence.entity.Type;
import com.nmatute.octoger.typemanagement.persistence.mapper.TypeMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TypeRepository implements ITypeRepository {

    ITypeCrudRepository crud;
    TypeMapper mapper;

    @Override
    public List<TypeDTO> getAll() {
        return mapper.toTypeDTOs((List<Type>) crud.findAll());
    }

    @Override
    public Optional<TypeDTO> getById(int id) {
        return crud.findById(id).map(type -> mapper.toTypeDTO(type));
    }

    @Override
    public List<TypeDTO> getByIdentifier(String identifier) {
        return mapper.toTypeDTOs(crud.findByIdentifierWhereIdentifierLike(identifier));
    }

    @Override
    public TypeDTO save(TypeDTO type) {
        Type t = crud.save(mapper.toType(type));
        return mapper.toTypeDTO(t);
    }

    @Override
    public void delete(int id) {
        crud.deleteById(id);
    }
    
}
