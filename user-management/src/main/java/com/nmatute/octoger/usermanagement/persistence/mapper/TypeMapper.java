package com.nmatute.octoger.usermanagement.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.nmatute.octoger.usermanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.usermanagement.persistence.entity.Type;

@Mapper(componentModel = "spring")
public interface TypeMapper {
    
    TypeDTO toTypeDTO(Type type);

    Type toType(TypeDTO typeDTO);

    List<TypeDTO> toTypeDTOs(List<Type> types);

    List<Type> toTypes(List<TypeDTO> typeDTOs);

}
