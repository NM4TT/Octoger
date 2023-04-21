package com.nmatute.octoger.typemanagement.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.nmatute.octoger.typemanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.typemanagement.persistence.entity.Type;

/**
 * Clase para Mapper de Tipos.
 * 
 * @author NM4TT
 */
@Mapper(componentModel = "spring")
public interface TypeMapper {
    
    TypeDTO toTypeDTO(Type type);

    Type toType(TypeDTO typeDTO);

    List<TypeDTO> toTypeDTOs(List<Type> types);

    List<Type> toTypes(List<TypeDTO> typeDTOs);

}
