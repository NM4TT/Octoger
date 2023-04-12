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

    private final ITypeCrudRepository crud;
    private final TypeMapper mapper;

    @Override
    public List<TypeDTO> getAll() {
        return mapper.toTypeDTOs((List<Type>) crud.findAll());
    }

    @Override
    public Optional<TypeDTO> getByIdentifier(String identifier) {
        return Optional.of(mapper.toTypeDTO(crud.findByIdentifier(identifier)));
    }

    @Override
    public TypeDTO save(TypeDTO user) {
        Type type = mapper.toType(user);
        return mapper.toTypeDTO(crud.save(type));
    }

    @Override
    public void delete(int id) {
        crud.deleteById(id);
    }

    @Override
    public Optional<TypeDTO> getById(int id) {
        return Optional.of(mapper.toTypeDTO(crud.findById(id).get()));
    }

    public Optional<List<TypeDTO>> getByPrefix(String prefix){
        return Optional.of(mapper.toTypeDTOs(crud.findByPrefix(prefix)));
    }
    
}
