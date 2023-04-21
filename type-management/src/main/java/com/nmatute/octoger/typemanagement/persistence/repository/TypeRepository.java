package com.nmatute.octoger.typemanagement.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.typemanagement.domain.dao.ITypeRepository;
import com.nmatute.octoger.typemanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.typemanagement.persistence.crud.ITypeCrudRepository;
import com.nmatute.octoger.typemanagement.persistence.entity.Type;
import com.nmatute.octoger.typemanagement.persistence.mapper.TypeMapper;

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
    public List<TypeDTO> getAll() {
        return mapper.toTypeDTOs((List<Type>) crud.findAll());
    }

    @Override
    public TypeDTO getByIdentifier(String identifier) {
        return mapper.toTypeDTO(crud.findByIdentifier(identifier));
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
    public TypeDTO getById(int id) {
        TypeDTO type = mapper.toTypeDTO(crud.findById(id).orElse(null));
        return type;
    }

    @Override
    public List<TypeDTO> getByPrefix(String prefix){
        return mapper.toTypeDTOs(crud.findByPrefix(prefix));
    }
    
}
